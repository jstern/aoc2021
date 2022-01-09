package aoc2021.day17;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class Day17Tests {

    static String INPUT = "target area: x=20..30, y=-10..-5\n";
    static ProbeLauncher.Target TARGET = ProbeLauncher.Target.describedBy(INPUT);

    @Test
    @DisplayName("day 17 part 1 example")
    void day17part1() {
        var launcher = new ProbeLauncher(INPUT);
        assertEquals(45, launcher.highestPossibleY());
    }

    @Test
    @DisplayName("day 17 part 2 example")
    void day17part2() {
        var launcher = new ProbeLauncher(INPUT);
        assertEquals(112, launcher.possibleInitialVelocities());
    }

}
