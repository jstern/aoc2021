package aoc2021.day0;

import aoc2021.Solution;

import java.util.stream.Collectors;

public final class Day0 implements Solution {

    /**
     * Santa needs us to remove the newlines to save X\nmas!
     */
    public final String part1(String input) {
        return input.lines().collect(Collectors.joining());
    }

    /**
     * How many presents are present in Santa's bag?
     */
    public final Integer part2(String input) {
        return input.lines().mapToInt(Integer::valueOf).sum();
    }
}
