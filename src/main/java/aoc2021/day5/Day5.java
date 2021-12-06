package aoc2021.day5;

import aoc2021.Solution;

public final class Day5 implements Solution {
    public Object part1(String input) {
        return new VentMap(input, 1000).countHazards();
        //return new BaroqueVentMap(input).countHazards();
    }

    public Object part2(String input) {
        return new VentMap(input, 1000, true).countHazards();
    }
}
