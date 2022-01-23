package aoc2021.day21;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Day21Tests {

    static String INPUT = "";

    @Test
    void testDeterministicDie() {
        var die = new DeterministicDie();
        var results = new ArrayList<Integer>();
        IntStream.range(0, 101).forEach(i -> results.add(die.roll()));
        assertEquals(1, results.get(0));
        assertEquals(2, results.get(1));
        assertEquals(100, results.get(99));
        assertEquals(1, results.get(100));
    }

    @Test
    @DisplayName("day 21 part 1 example")
    void day21part1() {
        var game = new DiracDiceGame(new DeterministicDie(), 4, 8);
        game.play();
        assertEquals(739785, game.getLoser().score() * game.getRolls());
    }

    @Test
    @DisplayName("day 21 part 2 example")
    @Disabled
    void day21part2() {
        assertEquals(true, false);
    }

}
