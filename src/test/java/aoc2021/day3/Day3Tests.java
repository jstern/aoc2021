package aoc2021.day3;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Day3Tests {

    String INPUT = """
            00100
            11110
            10110
            10111
            10101
            01111
            00111
            11100
            10000
            11001
            00010
            01010""";


    @Test
    @DisplayName("part one example")
    void partOne() {
        var bs = new BitSifter(INPUT.lines());
        assertEquals(22, bs.gamma);
        assertEquals(9, bs.epsilon);
    }

    @Test
    @DisplayName("part two o2 example")
    void partTwoO2() {
        var bs = new BitSifter(INPUT.lines());
        var o2 = bs.sift(true);
        assertEquals(23, o2);
    }

    @Test
    @DisplayName("part two co2 example")
    void partTwoCO2() {
        var bs = new BitSifter(INPUT.lines());
        INPUT.lines().forEach(bs::add);
        var co2 = bs.sift(false);
        assertEquals(10, co2);
    }
}
