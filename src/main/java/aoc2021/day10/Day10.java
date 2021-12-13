package aoc2021.day10;

import aoc2021.Solution;

public final class Day10 implements Solution {
    public Object part1(String input) {
        return NavigationSubsystem.errorScore(input);
    }

    public Object part2(String input) {
        return NavigationSubsystem.completionScore(input);
    }
}
