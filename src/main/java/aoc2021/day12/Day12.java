package aoc2021.day12;

import aoc2021.Solution;

public final class Day12 implements Solution {
    public Object part1(String input) {
        return new CaveMap(input).explore().size();
    }

    public Object part2(String input) {
        return new CaveMap(input, true).explore().size();
    }
}
