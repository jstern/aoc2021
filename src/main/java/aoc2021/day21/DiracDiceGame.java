package aoc2021.day21;

import aoc2021.Util;

import java.util.*;

public class DiracDiceGame {
    public static final List<Integer> SPACES = List.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);

    public record PlayerState(int player, int space, int score) {}

    int rolls = 0;
    int turnLimit = 100_000;
    final int target;
    Integer winner = null;
    List<PlayerState> players = new ArrayList<>();

    public DiracDiceGame(int p1, int p2, int target) {
        this.target = target;
        players.add(new PlayerState(1, p1, 0));
        players.add(new PlayerState(2, p2, 0));
    }

    public void play(Die die) {
        int turns = 0;
        int player = 0;
        while (turns < turnLimit) {
            var state = players.get(player);
            int newSpace = Util.wrappedGet(SPACES, state.space() + die.roll() + die.roll() + die.roll() - 1);
            rolls += 3;
            int newScore = state.score() + newSpace;
            state = new PlayerState(state.player(), newSpace, newScore);
            players.set(player, state);
            if (newScore >= this.target) {
                winner = player;
                return;
            }
            player = (player + 1) % 2;
            turns++;
        }
    }

    public List<PlayerState> getState() {
        return this.players;
    }

    public PlayerState getWinner() {
        return players.get(winner);
    }

    public PlayerState getLoser() {
        return Util.wrappedGet(players, winner + 1);
    }

    public int getRolls() {
        return this.rolls;
    }

    public static void allMoves() {
        Map<Integer,Set<String>> all = new HashMap<>();
        for (int a = 1; a < 4; a++ ) {
            for (int b = 1; b < 4; b++ ) {
                for (int c = 1; c < 4; c++ ) {
                    all.computeIfAbsent(a + b + c, k -> new HashSet<>()).add("%d%d%d".formatted(a, b, c));
                }
            }
        }
        System.out.println(all);
    }

    public static void allOutcomes() {
        Map<Integer,Set<StartMove>> all = new HashMap<>();

        for (int s = 0; s < 10; s++) {
            for (int m = 3; m < 10; m++) {
                var sm = new StartMove(SPACES.get(s), m);
                var landingSpace = Util.wrappedGet(SPACES, s + m);
                all.computeIfAbsent(landingSpace, k -> new HashSet<>()).add(sm);
            }
        }

        System.out.println(all);
        all.values().stream().map(s -> s.size()).forEach(System.out::println);
    }

    record StartMove(int start, int move) {
        public String toString() { return "%d+%d".formatted(start, move); }
    }


}
