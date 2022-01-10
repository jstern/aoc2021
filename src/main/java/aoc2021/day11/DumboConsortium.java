package aoc2021.day11;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class DumboConsortium {
    private final int[][] octopuses = new int[10][10];
    private int flashes = 0;

    public DumboConsortium(String input) {
        var ctx = new Object() {
            int row = 0;
        };
        input.lines().forEach(row -> {
            octopuses[ctx.row] = row.chars().map(v -> v - 48).toArray();
            ctx.row++;
        });
    }

    private void show() {
        System.out.println();
        for (int r = 0; r < 10; r++) {
            for (int c = 0; c < 10; c++) {
                var e = octopuses[r][c];
                if (e == 0) { System.out.print("\033[0;33m0\033[0m"); }
                else System.out.print(e);
            }
            System.out.print("\n");
        }
    }

    public int flashCount() {
        return flashes;
    }

    public int run(int steps) {
        int flashCountBefore = 0;
        //show();
        for (int i = 0; i < steps; i++) {
            step();
            if (flashCount() - flashCountBefore == 100) {
                //show();
                return i + 1;
            }
            flashCountBefore = flashCount();
            //show();
        }
        return -1;
    }

    public void step() {
        Set<Spot> flashed = new HashSet<>();
        Set<Spot> ready = charge();
        while(ready.size() > 0) {
            ready = propagate(ready, flashed);
        }
        this.flashes += flashed.size();
        discharge(flashed);
    }

    private Set<Spot> charge() {
        Set<Spot> ready = new HashSet<>();
        for (int r = 0; r < 10; r++) {
            for (int c = 0; c < 10; c++) {
                octopuses[r][c]++;
                if (octopuses[r][c] > 9) {
                    ready.add(new Spot(r, c));
                }
            }
        }
        return ready;
    }

    private Set<Spot> propagate(Set<Spot> ready, Set<Spot> flashed) {
        var newReady = new HashSet<Spot>();
        for (Spot spot : ready) {
            // don't flash again if we have already
            if (flashed.contains(spot)) continue;

            // remember that this spot has flashed
            flashed.add(spot);

            // increase energy level of all adjacent and record if they're now ready to flash for the first time
            spot.adjacent().forEach(s -> {
                octopuses[s.row()][s.col()]++;
                if (octopuses[s.row()][s.col()] > 9 && !flashed.contains(s)) {
                    newReady.add(s);
                }
            });
        }
        return newReady;
    }

    private void discharge(Set<Spot> flashed) {
        flashed.forEach(s -> octopuses[s.row][s.col] = 0);
    }

    record Spot(int row, int col) {
        List<Spot> adjacent() {
            var result = new ArrayList<Spot>();
            for (int dr = -1; dr < 2; dr++) {
                for (int dc = -1; dc < 2; dc++) {
                    var adj = new Spot(row + dr, col + dc);
                    if (validAdjacent(adj)) result.add(adj);
                }
            }
            return result;
        }

        boolean validAdjacent(Spot other) {
            return other.row >= 0 && other.row < 10 && other.col >= 0 && other.col < 10 && !this.equals(other);
        }
    }
}
