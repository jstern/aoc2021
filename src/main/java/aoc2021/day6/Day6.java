package aoc2021.day6;

import aoc2021.Solution;

public final class Day6 implements Solution {
    public Long part1(String input) {
        var pop = new LanternfishPopulation(input);
        pop.simulate(80);
        return pop.size();
    }

    public Long part2(String input) {
        var pop = new LanternfishPopulation(input);
        pop.simulate(256);
        return pop.size();
    }
}
