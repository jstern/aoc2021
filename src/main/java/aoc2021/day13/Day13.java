package aoc2021.day13;

import aoc2021.Solution;

public final class Day13 implements Solution {
    public Object part1(String input) {
        return new MapOfSomeTransparentPaper(input).countAfter(1);
    }

    public Object part2(String input) {
        return new MapOfSomeTransparentPaper(input).show();
    }
}
