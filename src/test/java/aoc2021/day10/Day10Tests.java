package aoc2021.day10;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Day10Tests {

    static String INPUT = """
            [({(<(())[]>[[{[]{<()<>>
            [(()[<>])]({[<{<<[]>>(
            {([(<{}[<>[]}>{[]{[(<()>
            (((({<>}<{<{<>}{[]{[]{}
            [[<[([]))<([[{}[[()]]]
            [{[{({}]{}}([{[{{{}}([]
            {<[[]]>}<{[{[{[]{()[[[]
            [<(<(<(<{}))><([]([]()
            <{([([[(<>()){}]>(<<{{
            <{([{{}}[<[[[<>{}]]]>[]]
            """;

    @Test
    @DisplayName("day 10 part 1 example")
    void day10part1() {
        assertEquals(26397, NavigationSubsystem.errorScore(INPUT));
    }

    @Test
    @DisplayName("day 10 part 2 example")
    void day10part2() {
        assertEquals(288957, NavigationSubsystem.completionScore(INPUT));
    }

}
