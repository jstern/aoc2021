package aoc2021.day14;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Day14Tests {

    static String INPUT = """
            NNCB
                        
            CH -> B
            HH -> N
            CB -> H
            NH -> C
            HB -> C
            HC -> B
            HN -> C
            NN -> C
            BH -> H
            NC -> B
            NB -> B
            BN -> B
            BB -> N
            BC -> B
            CC -> N
            CN -> C""";

    @Test
    @DisplayName("day 14 part 1 example")
    void day14part1() throws Exception {
        var sim = new PolymerSimulation(INPUT);
        var stats = sim.run(10);
        System.out.println(stats);
        assertEquals("H", stats.leastCommon());
        assertEquals(161, stats.leastCount());
        assertEquals("B", stats.mostCommon());
        assertEquals(1749, stats.mostCount());
    }

    @Test
    @DisplayName("day 14 part 2 example")
    @Disabled
    void day14part2() {
        assertEquals(true, false);
    }

}
