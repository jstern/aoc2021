package aoc2021.day2;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Day2Tests {

    static String DIRECTIONS = """
                forward 5
                down 5
                forward 8
                up 3
                down 8
                forward 2""";

    @Test
    @DisplayName("sub says what")
    void simpleSub() {
        var sub = new SixFootSub();
        DIRECTIONS.lines().forEach(s -> sub.move(s));
        assertEquals(15, sub.progress);
        assertEquals(10, sub.depth);
    }

    @Test
    @DisplayName("please to aim")
    void complexSub() {
        var sub = new Hoagie();
        DIRECTIONS.lines().forEach(s -> sub.move(s));
        assertEquals(15, sub.progress);
        assertEquals(60, sub.depth);
    }
}
