package aoc2021.day5;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class VentMap {
    private int[][] grid;

    public VentMap(String input, int max) {
        this(input, max, false);
    }

    public VentMap(String input, int max, boolean includeDiagonal) {
        grid = new int[max][max];
        input.lines().forEach(s -> {
            var line = read(s);
            if (includeDiagonal || line.horizontal() || line.vertical()) addVentLine(line);
        });

        if (System.getenv("DEBUG") == "1") System.out.println(Arrays.toString(grid));
    }

    static VentLine read(String desc) {
        var parts = desc.split(" -> ");
        return new VentLine(Point2D.of(parts[0]), Point2D.of(parts[1])).asIncreasingX();
    }

    private void addVentLine(VentLine line) {
       line.points().stream().forEach(p -> grid[p.y()][p.x()]++);
    }

    public int countHazards() {
        int total = 0;
        for (int y = 0; y < grid.length; y++) {
            for (int x = 0; x < grid.length; x++) {
                if (grid[y][x] > 1) total++;
            }
        }
        return total;
    }

    public static record VentLine(Point2D start, Point2D end) {
        int length() {
            if (horizontal()) return Math.abs(end.x() - start.x()) + 1;
            return Math.abs(end.y() - start.y()) + 1;
        }

        boolean horizontal() {
            return start.y() == end.y();
        }

        boolean vertical() {
            return start.x() == end.x();
        }

        VentLine asIncreasingX() {
            if (start.x() > end.x()) return new VentLine(end, start);
            return this;
        }

        int dx() {
            return vertical() ? 0 : 1;
        }

        int dy() {
            return horizontal() ? 0 : slope();
        }

        private int slope() {
            return start.y() < end.y() ? 1 : -1;
        }

        public List<Point2D> points() {
            List<Point2D> points = new ArrayList<>();
            Point2D point = start();
            points.add(point);
            for (int i = 1; i < length(); i++) {
                point = point.next(dx(), dy());
                points.add(point);
            }
            return points;
        }
    }
}
