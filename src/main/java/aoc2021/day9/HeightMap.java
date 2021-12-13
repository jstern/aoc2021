package aoc2021.day9;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

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

    private List<Point> adjacent(int x, int y) {
        var result = new ArrayList<Point>();
        if (x - 1 > -1)   addPoint(result, y, x - 1);
        if (x + 1 < xMax) addPoint(result, y, x + 1);
        if (y - 1 > -1)   addPoint(result, y - 1, x);
        if (y + 1 < yMax) addPoint(result, y + 1, x);
        return result;
    }

    private List<Point> adjacent(Point p) {
        return adjacent(p.x, p.y);
    }

    private void addPoint(List<Point> points, int y, int x) {
        points.add(new Point(y, x, heights.get(y).get(x)));
    }

    public List<Point> lowPoints() {
        var result = new ArrayList<Point>();
        for (int y = 0; y < yMax; y++) {
            for (int x = 0; x < xMax; x++) {
                Integer v = heights.get(y).get(x);
                List<Point> adj = adjacent(x, y);
                if (adj.stream().filter(a -> a.v > v).collect(Collectors.toList()).size() == adj.size()) result.add(new Point(y, x, v));
            }
        }
        return result;
    }

    public List<Set<Point>> basins() {
        return lowPoints().stream().map(this::basin).collect(Collectors.toList());
    }

    public Set<Point> basin(Point low) {
        var result = new HashSet<Point>();
        var adj = adjacent(low.x(), low.y()).stream().filter(p -> p.v() < 9).collect(Collectors.toList());
        result.addAll(adj); // we know these are all > low
        adj.forEach(point -> extendBasin(result, point));
        return result;
    }

    private void extendBasin(Set<Point> basin, Point p) {
        var adj = adjacent(p).stream().filter(p2 -> p2.v < 9).collect(Collectors.toList());
        for (Point a: adj) {
            if (basin.add(a)) extendBasin(basin, a);
        }
    }

    public record Point(int y, int x, int v) {
        public String toString() {
            return "(%d, %d): %d".formatted(x, y, v);
        }
    }
}
