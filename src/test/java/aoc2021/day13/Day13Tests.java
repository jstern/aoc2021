package aoc2021.day13;

import aoc2021.day5.Point2D;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class Day13Tests {

    static String INPUT = """
            6,10
            0,14
            9,10
            0,3
            10,4
            4,11
            6,0
            6,12
            4,1
            0,13
            10,12
            3,4
            3,0
            8,4
            1,10
            2,14
            8,10
            9,0
                        
            fold along y=7
            fold along x=5""";

    @Test
    @DisplayName("day 13 part 1 example")
    void day13part1() {
        var paper = new MapOfSomeTransparentPaper(INPUT);
        assertEquals(17, paper.countAfter(1));
    }

    @Test
    void foldUp() {
        assertEquals(Point2D.of("0,5"), MapOfSomeTransparentPaper.foldUp(Point2D.of("0,9"), 7).get());
        assertTrue(MapOfSomeTransparentPaper.foldUp(Point2D.of("0,7"), 7).isEmpty());
        assertEquals(Point2D.of("0,4"), MapOfSomeTransparentPaper.foldUp(Point2D.of("0,4"), 7).get());
    }

    @Test
    void foldLeft() {
        assertEquals(Point2D.of("5,0"), MapOfSomeTransparentPaper.foldLeft(Point2D.of("9,0"), 7).get());
        assertTrue(MapOfSomeTransparentPaper.foldLeft(Point2D.of("7,0"), 7).isEmpty());
        assertEquals(Point2D.of("4,0"), MapOfSomeTransparentPaper.foldLeft(Point2D.of("4,0"), 7).get());
    }

    @Test
    @DisplayName("day 13 part 2 example")
    @Disabled
    void day13part2() {
        assertEquals(true, false);
    }

}
