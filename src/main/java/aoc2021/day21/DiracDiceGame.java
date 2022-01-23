package aoc2021.day21;

import aoc2021.Util;

import java.util.ArrayList;
import java.util.List;

public class DiracDiceGame {
    public static final List<Integer> SPACES = List.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);

    public record PlayerState(int player, int space, int score) {}

    final Die die;
    int rolls = 0;
    int turnLimit = 100_000;
    Integer winner = null;
    List<PlayerState> players = new ArrayList<>();

    public DiracDiceGame(Die die, int p1, int p2) {
        this.die = die;
        players.add(new PlayerState(1, p1, 0));
        players.add(new PlayerState(2, p2, 0));
    }

    public void play() {
        int turns = 0;
        int player = 0;
        while (turns < turnLimit) {
            var state = players.get(player);
            int newSpace = Util.wrappedGet(SPACES, state.space() + die.roll() + die.roll() + die.roll() - 1);
            rolls += 3;
            int newScore = state.score() + newSpace;
            state = new PlayerState(state.player(), newSpace, newScore);
            players.set(player, state);
            if (newScore >= 1000) {
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
}
