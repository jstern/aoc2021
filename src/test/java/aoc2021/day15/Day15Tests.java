package aoc2021.day15;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class Day15Tests {

    static String INPUT = """
            1163751742
            1381373672
            2136511328
            3694931569
            7463417111
            1319128137
            1359912421
            3125421639
            1293138521
            2311944581""";

    @Test
    @DisplayName("shorter examples")
    void shorter() {
        String input = "12";
        var m = new RiskMap(input);
        m.leastRisk();
        assertEquals(2, m.leastRisk());

        input = "12\n13";
        m = new RiskMap(input);
        m.leastRisk();
        assertEquals(4, m.leastRisk());
    }

    @Test
    @DisplayName("day 15 part 1 example")
    void day15part1() {
        var m = new RiskMap(INPUT);
        assertEquals(40, m.leastRisk());
    }

    @Test
    @DisplayName("shorter part 2 examples")
    void shorter2() {
        var m = new RiskMap("12\n13", 2);
    }

    @Test
    @DisplayName("day 15 part 2 example")
    void day15part2() {
        var m = new RiskMap(INPUT, 5);
        assertEquals(315, m.leastRisk());
    }
}
