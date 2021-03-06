package aoc2021.day7;

import java.util.Arrays;
import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.stream.Collectors;

public class Crabs {
    private static class CrabCastException extends IllegalArgumentException {
        public CrabCastException(String msg) { super(msg); }
    }

    private final List<Integer> assortedCrabs;
    private final IntSummaryStatistics crabStats;

    public Crabs(String input) {
        assortedCrabs =
                Arrays
                        .stream(input.split(","))
                        .map(String::strip)
                        .map(Integer::parseInt)
                        .sorted()
                        .collect(Collectors.toList());
        crabStats = assortedCrabs.stream().mapToInt(i -> i).summaryStatistics();
        //System.out.println(crabStats);
    }

    public int scuttle() {
        List<Integer> left;
        List<Integer> right;
        int N = assortedCrabs.size();

        if (N % 2 == 0) {
            left = assortedCrabs.subList(0, N / 2);
            right = assortedCrabs.subList(N / 2, N);
            return costToScuttleTo(left, left.size() - 1) + costToScuttleTo(right, 0);
        }
        throw new CrabCastException("ignoring odd-sized casts of crabs for now");
    }

    private int costToScuttleTo(List<Integer> group, int idx) {
        int target = group.get(idx);
        return group.stream().mapToInt(v -> Math.abs(target - v)).sum();
    }

    public long crabwalk() {
        long target1 = Math.round(Math.floor(crabStats.getAverage()));
        long target2 = Math.round(crabStats.getAverage()); // yuck
        var t1result =  assortedCrabs.stream().mapToLong(v -> Math.abs(target1 - v) * (Math.abs(target1 - v) + 1) / 2).sum();
        var t2result =  assortedCrabs.stream().mapToLong(v -> Math.abs(target2 - v) * (Math.abs(target2 - v) + 1) / 2).sum();
        return Math.min(t1result, t2result);
    }
}
