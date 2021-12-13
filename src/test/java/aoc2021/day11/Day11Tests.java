package aoc2021.day11;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Day11Tests {

    static String INPUT = """
            5483143223
            2745854711
            5264556173
            6141336146
            6357385478
            4167524645
            2176841721
            6882881134
            4846848554
            5283751526""";

    @Test
    @DisplayName("day 11 part 1 example")
    void day11part1() {
        var dc = new DumboConsortium(INPUT);
        dc.run(2);
        assertEquals(35, dc.flashCount());
    }

    @Test
    @DisplayName("day 11 part 2 example")
    @Disabled
    void day11part2() {
        assertEquals(true, false);
    }

}
