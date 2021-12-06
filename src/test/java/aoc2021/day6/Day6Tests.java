package aoc2021.day6;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Day6Tests {
    static String INPUT = "3,4,3,1,2";

    @Test
    @DisplayName("day 6 part 1 example")
    void day6part1() {
        var pop = new LanternfishPopulation(INPUT);
        pop.show();
        assertEquals(5, pop.size());

        pop.simulate(18);
        assertEquals(26, pop.size());

        pop.simulate(80 - 18);
        assertEquals(5934, pop.size());
    }
}
