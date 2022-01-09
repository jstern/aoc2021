package aoc2021.day17;

import aoc2021.Solution;

public final class Day17 implements Solution {
    public Object part1(String input) {
        return new ProbeLauncher(input).highestPossibleY();
    }

    public Object part2(String input) {
        return new ProbeLauncher(input).possibleInitialVelocities();
    }
}
