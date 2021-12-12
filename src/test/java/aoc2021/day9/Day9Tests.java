package aoc2021.day9;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

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
        int result = new HeightMap(INPUT).lowPoints().stream().reduce(0, (t, c) -> t + 1 + c);
        assertEquals(15, result);
    }

    @Test
    @DisplayName("day 9 part 2 example")
    @Disabled
    void day9part2() {
        assertEquals(true, false);
    }

}
