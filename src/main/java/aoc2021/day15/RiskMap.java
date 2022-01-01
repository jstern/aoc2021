package aoc2021.day15;

import aoc2021.Util;
import aoc2021.day5.Point2D;

import java.util.*;

public class RiskMap {
    private static final List<Integer> VALS = List.of(1, 2, 3, 4, 5, 6, 7, 8, 9);
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
        // write down the risk for each point in the base tile
        var baseRisks = new HashMap<Point2D,Integer>();
        var ctx = new Object() {
            int xMax;
            int y = 0;
        };
        input.lines().forEach(s -> {
            for (int i = 0; i < s.length(); i++) {
                ctx.xMax = i;
                baseRisks.put(new Point2D(i, ctx.y), Integer.parseInt(s.substring(i, i + 1), 10));
            }
            ctx.y++;
        });

        // Remember the maxima of the base tile
        var xs = ctx.xMax;
        var ys = ctx.y - 1;
        //System.out.printf("base maxes: %d,%d%n", xs, ys);

        // Calculate the overall maxima based on number of tile
        xMax = ((ctx.xMax + 1) * N) - 1;
        yMax = (ctx.y * N) - 1;          // ctx.y is already 1 larger than maxY for 1 tile
        end = new Point2D(xMax, yMax);

        // Now create any additional tiles in the grid based on the root tile, and build the full risks map
        for (int dy = 0; dy < N; dy++) {
            for (int dx = 0; dx < N; dx++) {
                //System.out.printf("\nBuilding tile %d,%d%n", dx, dy);
                for (var basePoint : baseRisks.entrySet()) {
                    addPoint(basePoint.getKey(), basePoint.getValue(), dx, dy, xs, ys);
                }
            }
        }

        // then build the graph
        for (var p : risks.keySet()) {
            var adj = graph.computeIfAbsent(p, k -> new HashMap<>());
            for (var n : neighbors(p)) {
                adj.put(n, risks.get(n));
            }
        }
        // show full grid with base tile highlighted:
        // show(baseRisks.keySet());
    }

    /**
     * Calculate coordinates and associated risk of a point based on
     * which tile the original point is repeated in.
     *
     * @param bp base Point2D
     * @param br risk value of bp
     * @param dx number of tiles to the right of the base tile
     * @param dy number of tiles down from the base tile
     * @param xs max x value in base tile
     * @param ys max y value in base tile
     */
    private void addPoint(Point2D bp, Integer br, int dx, int dy, int xs, int ys) {
        var r = Util.wrappedGet(VALS, br + dx + dy - 1);  // hey look I used some day 0 code :)
        var p = new Point2D(bp.x() + (dx * (xs + 1)), bp.y() + (dy * (ys + 1)));
        //System.out.println(bp + ": " + br + " -> " + p + ": " + r);
        risks.put(p, r);
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
        var dist = new HashMap<Point2D,Integer>();  // "distances" from start
        dist.put(start, 0);
        var prev = new HashMap<Point2D,Point2D>();  // parents
        var next = new PriorityQueue<PointPlusDist>(Comparator.comparingInt(PointPlusDist::dist));
        next.add(new PointPlusDist(start, 0));

        for (var point : graph.keySet()) {
            if (!point.equals(start)) {
                dist.put(point, Integer.MAX_VALUE);
                prev.put(point, null);
            }
        }

        while (!next.isEmpty()) {
            var u = next.poll();

            // don't process points where we already have a different (shorter) distance
            if (!dist.get(u.point()).equals(u.dist())) continue;

            // FIXME?: risk values/neighbors are in graph already, don't need to call neighbors and look at risks
            for (var n : neighbors(u.point())) {
                var cost = dist.get(u.point()) + risks.get(n);
                if (cost < dist.get(n)) {
                    dist.put(n, cost);
                    prev.put(n, u.point());
                    next.add(new PointPlusDist(n, cost));
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

    static record PointPlusDist(Point2D point, Integer dist) {}

    private void show(Set<Point2D> path) {
        for (int y = 0; y <= yMax; y++) {
            for (int x = 0; x <= xMax; x++) {
                var p = new Point2D(x, y);
                var r = risks.get(p);
                if (r == null) r = 0;
                if (path.contains(p)) {
                    System.out.print("\u001B[33m" + r + "\u001B[0m");
                } else {
                    System.out.print(r);
                }
            }
            System.out.print("\n");
        }
    }
}
