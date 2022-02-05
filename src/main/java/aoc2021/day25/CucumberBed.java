package aoc2021.day25;

import aoc2021.Util;
import aoc2021.day5.Point2D;

import java.util.*;

public class CucumberBed {
    enum Herd {
        EAST('>', 1, 0), SOUTH('v', 0, 1);

        final char repr;
        final int dx;
        final int dy;

        Herd(char repr, int dx, int dy) {
            this.repr = repr;
            this.dx = dx;
            this.dy = dy;
        }

        Point2D movesTo(Point2D from, int maxX, int maxY) {
            return new Point2D(Util.wrappedIndex(maxX, from.x() + dx), Util.wrappedIndex(maxY, from.y() + dy));
        }
    }
    static Map<Character, Herd> HERDS = Map.of('v', Herd.SOUTH, '>', Herd.EAST);

    Herd[][] grid;

    public CucumberBed(String input) {
        grid = new Herd[(int)input.lines().count()][input.lines().findAny().get().length()];
        var ctx = new Object() {
            int y = 0;
            int x = 0;
        };
        input.lines().forEach(row -> {
            Herd h = null;
            Point2D p = null;
            for (int i = 0; i < row.length(); i++) {
                ctx.x = i;
                p = new Point2D(ctx.x, ctx.y);
                h = HERDS.get(row.charAt(i));
                grid[ctx.y][ctx.x] = h;
            }
            ctx.y++;
        });
    }

    public boolean move(Herd herd) {
        Map<Point2D, Herd> updates = new HashMap<>();
        Herd[] row;
        Point2D currentPoint, maybeDest;
        Herd atCurrent, atDest;

        for (int y = 0; y < grid.length; y++) {
            row = grid[y];
            for (int x = 0; x < row.length; x++) {
                atCurrent = grid[y][x];
                if (atCurrent == herd) {
                    currentPoint = new Point2D(x, y);
                    maybeDest = atCurrent.movesTo(currentPoint, row.length, grid.length);
                    atDest = grid[maybeDest.y()][maybeDest.x()];
                    //System.out.printf("%s:%s -> %s:%s%n", currentPoint, atCurrent, maybeDest, atDest);
                    if (atDest == null) {
                        updates.put(maybeDest, herd);
                        updates.put(currentPoint, null);
                    }
                }
            }
        }
        updates.forEach((p, h) -> grid[p.y()][p.x()] = h);
        return updates.size() != 0;
    }

    public int findEquilibriumStep() {
        int steps = 0;
        boolean movedEast, movedSouth;
        while (true) {
            steps++;
            movedEast = move(Herd.EAST);
            movedSouth = move(Herd.SOUTH);
            if (!(movedEast || movedSouth)) break;
        }
        return steps;
    }

    public void show() {
        for (int y = 0; y < grid.length; y++) {
            var row = grid[y];
            for (int x = 0; x < row.length; x++ ) {
                var h = grid[y][x];
                var r = (h == null ? '.' : h.repr);
                System.out.print(r);
            }
            System.out.print("\n");
        }
        System.out.println();
    }
}
