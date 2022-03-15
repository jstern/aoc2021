package aoc2021.day21;

import aoc2021.Solution;

import java.util.Comparator;
import java.util.Map;

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
         * So... finding all the series of 3-rolls that result in a complete game, and
         * then multiplying each 3-roll by the number of ways it could have happened.
         */
        Map<Part2Reboot.Winner, Long> totals = Part2Reboot.finalStates(Part2Reboot.START, 21);
        //System.out.println(totals);
        return totals.values().stream().max(Comparator.naturalOrder());
        // 1452372287 too low!
        // 157595953724471
    }
}
