package aoc2021.day9;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class Day9Tests {

    static String INPUT = """
            2199943210
            3987894921
            9856789892
            8767896789
            9899965678""";

    @Test
    @DisplayName("day 9 part 1 example")
    void day9part1() {
        int result = new HeightMap(INPUT).lowPoints().stream().map(HeightMap.Point::v).reduce(0, (t, c) -> t + 1 + c);
        assertEquals(15, result);
    }

    @Test
    @DisplayName("day 9 part 2 example")
    void day9part2() {
        var basins = new HeightMap(INPUT).basins().stream().sorted(Comparator.comparing(Set::size)).collect(Collectors.toList());
        var result = basins.subList(basins.size() - 3, basins.size()).stream().map(Set::size).reduce(1, (a, b) -> a * b);
        assertEquals(1134, result);
    }

}
