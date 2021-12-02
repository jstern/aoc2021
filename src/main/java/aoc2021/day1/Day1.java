package aoc2021.day1;

import aoc2021.Solution;

public final class Day1 extends Solution {
    public final Object part1(String input) {
        DepthScan scan = new DepthScan(input);
        return scan.countChanges(1);
    }

    public final Object part2(String input) {
        DepthScan scan = new DepthScan(input);
        return scan.countChanges(3);
    }
}
