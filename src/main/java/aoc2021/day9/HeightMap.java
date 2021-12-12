package aoc2021.day9;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class HeightMap {
    private List<List<Integer>> heights = new ArrayList<>();
    private int xMax;
    private int yMax;

    public HeightMap(String input) {
        input.lines().forEach(ln -> {
            heights.add(ln.chars().mapToObj(v -> v - 48).collect(Collectors.toList()));
        });
        xMax = heights.get(0).size();
        yMax = heights.size();
    }

    private List<Integer> adjacent(int x, int y) {
        var result = new ArrayList<Integer>();
        if (x - 1 > -1) result.add(heights.get(y).get(x - 1));
        if (x + 1 < xMax) result.add(heights.get(y).get(x + 1));
        if (y - 1 > -1) result.add(heights.get(y - 1).get(x));
        if (y + 1 < yMax) result.add(heights.get(y + 1).get(x));
        return result;
    }

    public List<Integer> lowPoints() {
        var result = new ArrayList<Integer>();
        for (int y = 0; y < yMax; y++) {
            for (int x = 0; x < xMax; x++) {
                Integer v = heights.get(y).get(x);
                List<Integer> adj = adjacent(x, y);
                if (adj.stream().filter(a -> a > v).collect(Collectors.toList()).size() == adj.size()) result.add(v);
            }
        }
        return result;
    }
}
