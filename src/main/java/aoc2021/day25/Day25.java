package aoc2021.day25;

import aoc2021.Solution;

public final class Day25 implements Solution {
    public Object part1(String input) {
        // 256 too low
        return new CucumberBed(input).findEquilibriumStep();
    }

    public Object part2(String input) {
        return "";
    }
}
