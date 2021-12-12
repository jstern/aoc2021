package aoc2021.day9;

import aoc2021.Solution;

public final class Day9 implements Solution {
    public Object part1(String input) {
        return new HeightMap(input).lowPoints().stream().reduce(0, (t, c) -> t + 1 + c);
    }

    public Object part2(String input) {
        return "";
    }
}
