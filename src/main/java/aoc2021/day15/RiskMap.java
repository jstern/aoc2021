package aoc2021.day15;

import aoc2021.day5.Point2D;

import java.util.*;

public class RiskMap {
    private final Map<Point2D,Map<Point2D,Integer>> graph = new HashMap<>();
    private final Map<Point2D,Integer> risks = new HashMap<>();
    private final Point2D start = new Point2D(0, 0);
    private final Point2D end;
    private final int xMax;
    private final int yMax;

    public RiskMap(String input) {
        this(input, 1);
    }

    public RiskMap(String input, int N) {
        // write down the risk for each point and figure out size of grid (or first grid tile)
        var ctx = new Object() {
            int xMax;
            int y = 0;
        };
        input.lines().forEach(s -> {
            for (int i = 0; i < s.length(); i++) {
                ctx.xMax = i;
                risks.put(new Point2D(i, ctx.y), Integer.parseInt(s.substring(i, i + 1), 10));
            }
            ctx.y++;
        });

        xMax = ctx.xMax;
        yMax = ctx.y - 1;
        end = new Point2D(xMax, yMax);

        // then build the graph
        for (var p : risks.keySet()) {
            var adj = graph.computeIfAbsent(p, k -> new HashMap<Point2D,Integer>());
            for (var n : neighbors(p)) {
                adj.put(n, risks.get(n));
            }
        }
    }

    private List<Point2D> neighbors(Point2D current) {
        var result = new ArrayList<Point2D>();
        addIfValid(current.next(-1,0), result);
        addIfValid(current.next(1, 0), result);
        addIfValid(current.next(0, -1), result);
        addIfValid(current.next(0, 1), result);
        return result;
    }

    private void addIfValid(Point2D p, List<Point2D> points) {
        if (p.x() < 0 || p.x() > xMax || p.y() < 0 || p.y() > yMax) return;
        points.add(p);
    }

    public int leastRisk() {
        return leastRisk(false);
    }

    // from https://en.wikipedia.org/wiki/Dijkstra%27s_algorithm
    public int leastRisk(boolean show) {
        var unprocessed = new HashSet<Point2D>(graph.keySet());
        var dist = new HashMap<Point2D,Integer>();
        var prev = new HashMap<Point2D,Point2D>();

        for (var point : graph.keySet()) {
            dist.put(point, Integer.MAX_VALUE);
            prev.put(point, null);
        }
        dist.put(start, 0);

        while (!unprocessed.isEmpty()) {
            var u = unprocessed.stream().min(Comparator.comparingInt(dist::get)).get();
            unprocessed.remove(u);

            // FIXME: risk values/neighbors are in graph already, don't need to call neighbors and look at risks
            for (var n : neighbors(u)) {
                var cost = dist.get(u) + risks.get(n);
                if (cost < dist.get(n)) {
                    dist.put(n, cost);
                    prev.put(n, u);
                }
            }
        }

        if (show) {
            var path = new ArrayList<Point2D>();
            var p = end;
            if (prev.get(p) != null || p.equals(start)) {
                while (p != null) {
                    path.add(p);
                    p = prev.get(p);
                }
            }
            Collections.reverse(path);
            show(new HashSet<>(path));
        }

        return dist.get(end);
    }

    private void show(Set<Point2D> path) {
        for (int y = 0; y <= yMax; y++) {
            for (int x = 0; x <= xMax; x++) {
                var p = new Point2D(x, y);
                if (path.contains(p)) {
                    System.out.print("\u001B[33m" + risks.get(p) + "\u001B[0m");
                } else {
                    System.out.print(risks.get(p));
                }
            }
            System.out.print("\n");
        }
    }
}
