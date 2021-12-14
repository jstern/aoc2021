package aoc2021.day11;

import aoc2021.Solution;

public final class Day11 implements Solution {
    public Object part1(String input) {
        var consortium = new DumboConsortium(input);
        consortium.run(100);
        return consortium.flashCount();
    }

    public Object part2(String input) {
        var consortium = new DumboConsortium(input);
        return consortium.run(Integer.MAX_VALUE);
    }
}
