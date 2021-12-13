package aoc2021.day9;

import aoc2021.Solution;

import java.util.Comparator;
import java.util.Set;
import java.util.stream.Collectors;

public final class Day9 implements Solution {
    public Object part1(String input) {
        return new HeightMap(input).lowPoints().stream().map(p -> p.v()).reduce(0, (t, c) -> t + c + 1);
    }

    public Object part2(String input) {
        var basins = new HeightMap(input).basins().stream().sorted(Comparator.comparing(Set::size)).collect(Collectors.toList());
        return basins.subList(basins.size() - 3, basins.size()).stream().map(Set::size).reduce(1, (a, b) -> a * b);
    }
}
