package aoc2021.day8;

import aoc2021.Solution;

import java.util.Arrays;

public final class Day8 implements Solution {
    public Object part1(String input) {
        var ctx = new Object() {
            int count = 0;
        };
        input.lines().forEach(entry -> {
            Arrays.stream(SevenSegmentDisplay.parseLine(entry).outputs()).forEach(o -> {
                SevenSegmentDisplay.getSingleDigit(o).ifPresent(v -> ctx.count++);
            });
        });
        return ctx.count;
    }

    public Object part2(String input) {
        var ctx = new Object() { int sum = 0; };
        input.lines().forEach(e -> { ctx.sum += SevenSegmentDisplay.parseLine(e).outputValue(); });
        return ctx.sum;
    }
}
