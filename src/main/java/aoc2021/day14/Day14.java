package aoc2021.day14;

import aoc2021.Solution;

public final class Day14 implements Solution {
    public Object part1(String input) {
        var sim = new PolymerSimulation(input);
        try { sim.run(10); } catch (Exception x) { return x; }
        var stats = sim.stats();
        return stats.mostCount() - stats.leastCount();
    }

    public Object part2(String input) {
        var sim = new PolymerSimulation(input);
        try { sim.run(40); } catch (Exception x) { return x; }
        var stats = sim.stats();
        return stats.mostCount() - stats.leastCount();
    }
}
