package aoc2021.day14;

import java.util.*;

public class PolymerSimulation {
    private final Map<String,String> rules = new HashMap<>();
    private final Map<String,Long> elements = new HashMap<>();
    private Map<String,Long> pairCounts;

    public PolymerSimulation(String input) {
        input.lines().forEach(s -> {
            if (null == pairCounts) {
                //System.out.println(s);
                pairCounts = new HashMap<>();
                String first = s.substring(0, 1);
                count(first, 1);
                for (int i = 1; i < s.length(); i++) {
                    String second = s.substring(i, i +1);
                    count(second, 1);
                    countPair(s.substring(i - 1, i + 1));
                }
            } else if (s.strip().length() > 0) {
                rules.put(s.substring(0,2), s.substring(s.length() -1));
            }
        });
        //System.out.println(rules);
        //System.out.println(elements);
        //System.out.println(pairCounts);
    }

    public Stats run(int steps) {
        for (int i = 0; i < steps; i++) {
            var newCounts = new HashMap<String,Long>();
            pairCounts.forEach((p, N) -> {
                var c = rules.get(p);
                count(c, N);
                var lft = p.charAt(0) + c;
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

    private void countPair(String p) {
        pairCounts.put(p, pairCounts.getOrDefault(p, 0L) + 1L);
    }

    public record Stats(String leastCommon, long leastCount, String mostCommon, long mostCount) {}

    public Stats stats() {
        var sorted = elements.entrySet().stream().sorted(Comparator.comparingLong(Map.Entry::getValue)).map(Map.Entry::getKey).toList();
        return new Stats(
                sorted.get(0),
                elements.get(sorted.get(0)),
                sorted.get(sorted.size() - 1),
                elements.get(sorted.get(sorted.size() - 1)));
    }
}
