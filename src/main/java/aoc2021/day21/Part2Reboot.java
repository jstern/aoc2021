package aoc2021.day21;

import aoc2021.Util;

import java.util.*;

public class Part2Reboot {

    // outcome -> number of ways to get them
    static final Map<Integer,Integer> THREE_ROLLS = Map.of(
            3, 1,
            4, 3,
            5, 6,
            6, 7,
            7, 6,
            8, 3,
            9, 1
    );

    // game spaces (point values)
    static final List<Integer> SPACES = List.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);

    record PlayerState(int space, int score) {
        PlayerState next(int roll) {
            int landing = Util.wrappedGet(SPACES, space + roll - 1);
            return new PlayerState(landing, score + landing);
        }
    }

    enum Winner { ONE, TWO; }

    record GameState(PlayerState p1, PlayerState p2, boolean p1turn, String history) {
        GameState next(int roll) {
            String hist = history + roll;
            if (p1turn) {
                return new GameState(p1.next(roll), p2, false, hist);
            } else {
                return new GameState(p1, p2.next(roll), true, hist);
            }
        }

        Optional<Winner> winner(int threshold) {
            if (p1().score() >= threshold) { return Optional.of(Winner.ONE); }
            if (p2().score() >= threshold) { return Optional.of(Winner.TWO); }
            return Optional.empty();
        }
    }

    static final GameState START = new GameState(
            new PlayerState(9, 0), new PlayerState(10, 0), true, "");

    static Map<Winner,Long> finalStates(GameState currentState, int threshold) {
        if (currentState.winner(threshold).isPresent()) {
            return Map.of(currentState.winner(threshold).get(), countAll(currentState.history)); }

        Map<Winner,Long> childStates = new HashMap<>();
        for (int roll: THREE_ROLLS.keySet()) {
            var nextState = currentState.next(roll);
            finalStates(nextState, threshold).forEach((w, c) -> {
                long total = childStates.getOrDefault(w, 0L);
                childStates.put(w, total + c);
            });
        }
        return childStates;
    }

    static long countAll(String rolls) {
        long total = 1;

        for (int i = 0; i < rolls.length(); i++) {
            total *= THREE_ROLLS.get(Integer.parseInt(rolls.substring(i, i+1)));
        }

        return total;
    }
}
