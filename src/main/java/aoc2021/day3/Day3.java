package aoc2021.day3;

import aoc2021.Solution;

public final class Day3 implements Solution {
    public final Object part1(String input) {
        var bs = new BitSifter(input.lines());
        return bs.gamma * bs.epsilon;
    }

    public final Object part2(String input) {
        var bs = new BitSifter(input.lines());
        return bs.sift(true) * bs.sift(false);
    }
}
