package aoc2021.day7;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class Day7Tests {

    static String INPUT = "16,1,2,0,4,2,7,1,2,14";

    @Test
    @DisplayName("day 7 part 1 example")
    void day7part1() {
        var crabs = new Crabs(INPUT);
        assertEquals(37, crabs.scuttle());
    }

    @Test
    @DisplayName("day 7 part 2 example")
    @Disabled
    void day7part2() {
        assertEquals(true, false);
    }

}
