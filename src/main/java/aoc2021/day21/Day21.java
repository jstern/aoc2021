package aoc2021.day21;

import aoc2021.Solution;

public final class Day21 implements Solution {
    public Object part1(String input) {
        var game = new DiracDiceGame(new DeterministicDie(), 9, 10);
        game.play();
        return game.getLoser().score() * game.getRolls();
    }

    public Object part2(String input) {
        return "";
    }
}
