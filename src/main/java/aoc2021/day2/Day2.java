package aoc2021.day2;

import aoc2021.Solution;

public final class Day2 implements Solution {
    public final Object part1(String input) {
        var sub = new SixFootSub();
        input.lines().forEach(sub::move);
        return sub.progress * sub.depth;
    }

    public final Object part2(String input) {
        var sub = new Hoagie();
        input.lines().forEach(sub::move);
        return sub.progress * sub.depth;
    }
}
