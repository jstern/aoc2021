package aoc2021.day14;

import java.util.*;

public class PolymerSimulation {
    private Map<String,String> rules = new HashMap<>();
    private Map<String,Long> elements = new HashMap<>();
    private Map<String,Long> pairCounts;

    public static record Pair(char first, char second) {}

    public PolymerSimulation(String input) {
        input.lines().forEach(s -> {
            if (null == pairCounts) {
                System.out.println(s);
                pairCounts = new HashMap<>();
                String first = s.substring(0, 1);
                count(first, 1);
                for (int i = 1; i < s.length(); i++) {
                    String second = s.substring(i, i +1);
                    count(second, 1);
                    countPair(s.substring(i - 1, i + 1), 1L);
                }
            } else if (s.strip().length() > 0) {
                var pair = new Pair(s.charAt(0), s.charAt(1));
                rules.put(s.substring(0,2), s.substring(s.length() -1));
            }
        });
        System.out.println(rules);
        System.out.println(elements);
        System.out.println(pairCounts);
    }

    public Stats run(int steps) {
        for (int i = 0; i < steps; i++) {
            var newCounts = new HashMap<String,Long>();
            pairCounts.entrySet().forEach(e -> {
                var p = e.getKey();
                var N = e.getValue();
                var c = rules.get(p);
                count(c, N);
                var lft = p.substring(0, 1) + c;
                var rgt = c + p.substring(1);
                newCounts.put(lft, newCounts.getOrDefault(lft, 0L) + N);
                newCounts.put(rgt, newCounts.getOrDefault(rgt, 0L) + N);
            });
            pairCounts = newCounts;
        }
        return stats();
    }

    private void count(String c, long times) {
        elements.put(c, elements.getOrDefault(c, 0L) + times);
    }

    private void countPair(String p, long times) {
        pairCounts.put(p, pairCounts.getOrDefault(p, 0L) + times);
    }

    public static record Stats(String leastCommon, long leastCount, String mostCommon, long mostCount) {}

    public Stats stats() {
        var sorted = elements.entrySet().stream().sorted(Comparator.comparingLong(Map.Entry::getValue)).map(e -> e.getKey()).toList();
        return new Stats(
                sorted.get(0),
                elements.get(sorted.get(0)),
                sorted.get(sorted.size() - 1),
                elements.get(sorted.get(sorted.size() - 1)));
    }
}
