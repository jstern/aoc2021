package aoc2021.day16;

import aoc2021.Solution;

public final class Day16 implements Solution {
    public Object part1(String input) {
        return BITS.readerFromHex(input.strip()).read().sumVersions();
    }

    public Object part2(String input) {
        return BITS.readerFromHex(input.strip()).read().value();
    }
}
