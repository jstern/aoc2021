package aoc2021.day15;

import aoc2021.Solution;

public final class Day15 implements Solution {
    public Object part1(String input) {
       var m = new RiskMap(input);
       return m.leastRisk();
    }

    public Object part2(String input) {
        var m = new RiskMap(input, 5);
        return m.leastRisk();
    }
}
