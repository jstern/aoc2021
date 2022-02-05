package aoc2021.day25;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class Day25Tests {

    static String INPUT = """
            v...>>.vv>
            .vv>>.vv..
            >>.>v>...v
            >>v>>.>.v.
            v>v.vv.v..
            >.>>..v...
            .vv..>.>v.
            v.v..>>v.v
            ....v..v.>""";

    @Test
    @DisplayName("day 25 part 1 example")
    void day25part1() {
        var bed = new CucumberBed("""
            v...>>.vv>
            .vv>>.vv..""");
        bed.show();
        assertTrue(bed.move(CucumberBed.Herd.EAST));
        assertTrue(bed.move(CucumberBed.Herd.SOUTH));
        bed.show();
    }

    @Test
    @DisplayName("day 25 part 2 example")
    @Disabled
    void day25part2() {
        assertEquals(true, false);
    }

}
