package aoc2021.day5;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class Day5Tests {

    static String INPUT = """
            0,9 -> 5,9
            8,0 -> 0,8
            9,4 -> 3,4
            2,2 -> 2,1
            7,0 -> 7,4
            6,4 -> 2,0
            0,9 -> 2,9
            3,4 -> 1,4
            0,0 -> 8,8
            5,5 -> 8,2""";

    static VentMap VENTS = new VentMap(INPUT, 10);

    @Test
    @DisplayName("day 5 part 1 example")
    void partOneExample() {
        assertEquals(5, VENTS.countHazards());
    }

    @Test
    @DisplayName("day 5 part 2 example")
    void partTwoExample() {
        assertEquals(12, new VentMap(INPUT, 10, true).countHazards());
    }
}
