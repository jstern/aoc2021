package aoc2021.day21;

import aoc2021.Solution;

public final class Day21 implements Solution {
    public Object part1(String input) {
        var game = new DiracDiceGame(9, 10, 1000);
        game.play(new DeterministicDie());
        return game.getLoser().score() * game.getRolls();
    }

    public Object part2(String input) {
        /*
         * part 2
         *
         * player 1 starts at 9, player 2 at 10. they play to 21
         * what are the fewest and most turns each needs to reach 21?
         *
         * Each turn can move the player between 3 and 9 spaces
         * {3=[111], 4=[121, 112, 211], 5=[122, 221, 113, 212, 311, 131], 6=[132, 231, 123, 222, 321, 213, 312], 7=[133, 232, 331, 223, 322, 313], 8=[233, 332, 323], 9=[333]}
         *       x
         *     x x x
         *     x x x
         *     x x x
         *   x x x x x
         *   x x x x x
         * x x x x x x x
         * 3 4 5 6 7 8 9
         *
         * Each turn can have 1 of 10 point outcomes (7 different ways each)
         *
         * for example
         *
         * 9 -> 2 (3)
         * 9 -> 3 (4)
         * 9 -> 4 (5)
         * 9 -> 5 (6)
         * 9 -> 6 (7)
         * 9 -> 7 (8)
         * 9 -> 8 (9)
         *
         * The next question I think is, what are all the sequences of 3-roll outcomes that get exactly one player to 21?
         *
         *
         */
        DiracDiceGame.allMoves();
        DiracDiceGame.allOutcomes();
        return "";
    }
}
