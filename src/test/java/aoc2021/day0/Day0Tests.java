package aoc2021.day0;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Day0Tests {

    final String input = "7\n9\n1";

    @Test
    @DisplayName("part1 removes newlines")
    void part1() {
        assertEquals("791", new Day0().part1(input));
    }

    @Test
    @DisplayName("part2 sums integers")
    void part2() {
        assertEquals(17, new Day0().part2(input));
    }
}
