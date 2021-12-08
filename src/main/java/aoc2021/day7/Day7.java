package aoc2021.day7;

import aoc2021.Solution;

public final class Day7 implements Solution {
    public Integer part1(String input) {
        return new Crabs(input).scuttle();
    }

    public Object part2(String input) {
        return new Crabs(input).crabwalk();
    }
}
