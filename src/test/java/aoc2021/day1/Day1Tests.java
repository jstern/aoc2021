package aoc2021.day1;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Day1Tests {

    static DepthScan SCAN = new DepthScan("""
            199
            200
            208
            210
            200
            207
            240
            269
            260
            263
            """);

    @Test
    @DisplayName("simple scan for depth increases")
    void simpleDepthScan() {
        assertEquals(7, SCAN.countChanges(1));
    }

    @Test
    @DisplayName("scan for sliding window of depth increases")
    void windowedDepthScan() {
        assertEquals(5, SCAN.countChanges(3));
    }
}
