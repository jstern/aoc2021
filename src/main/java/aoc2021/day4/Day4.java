package aoc2021.day4;

import aoc2021.Solution;

import java.util.stream.Collectors;

public final class Day4 implements Solution {
    public final Object part1(String input) {
        return BingoSession.findAndScoreWinner(input.lines().collect(Collectors.toList()));
    }

    public final Object part2(String input) {
        return BingoSession.findAndScoreWinner(input.lines().collect(Collectors.toList()), BingoSession.Strategy.LET_THE_SQUID_WIN);
    }
}
