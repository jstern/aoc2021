package aoc2021.day17;

import aoc2021.day5.Point2D;

import java.util.*;
import java.util.function.Function;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

// TODO: Do it with just math? (https://en.wikipedia.org/wiki/Projectile_motion#Trajectory_of_a_projectile_with_air_resistance)

public class ProbeLauncher {
    static final int TRIALS = 200;

    public record Target(int xMin, int xMax, int yMin, int yMax) {
        public static Target describedBy(String input) {
            Pattern p = Pattern.compile("^target area: x=([^.]+)\\.\\.([^,]+), y=([^.]+)\\.\\.(.*)$");
            Matcher m = p.matcher(input.strip());
            if (!m.matches()) throw new IllegalArgumentException("couldn't read target description");
            int x0 = Integer.parseInt(m.group(1));
            int x1 = Integer.parseInt(m.group(2));
            int y0 = Integer.parseInt(m.group(3));
            int y1 = Integer.parseInt(m.group(4));
            return new Target(Math.min(x0, x1), Math.max(x0, x1), Math.min(y0, y1), Math.max(y0, y1));
        }

        public boolean inXRange(int x) { return x >= xMin && x <= xMax; }

        public boolean inYRange(int y) { return y >= yMin && y <= yMax; }

        public boolean hitBy(int x, int y) { return inXRange(x) && inYRange(y); }

        public boolean missedBy(int x, int y, int vy) {
            return x > xMax() || (y < yMin() && vy < 0);
        }
    }

    public record MemoKey(int vInitial, int time) {}

    public record State(int position, int velocity) {}

    public record Result(boolean hit, List<Point2D> path) {
        public int yMax() {
            return path.stream().mapToInt(Point2D::y).max().getAsInt();
        }
    }

    public static int nextVx(int vx) {
        if (vx == 0) return 0;
        return vx < 0 ? vx + 1 : vx - 1;
    }

    public static int nextVy(int vy) {
        return vy - 1;
    }

    private final Target target;
    private final Map<MemoKey,State> xMemo = new HashMap<>();
    private final Map<MemoKey,State> yMemo = new HashMap<>();

    public ProbeLauncher(String input) {
        target = Target.describedBy(input);
    }

    public State atT(int vInitial, int t, Map<MemoKey,State> memo, Function<Integer,Integer> dv) {
        MemoKey key = new MemoKey(vInitial, t);
        State state = memo.get(key);
        if (state == null) {
            if (t == 0) {
                state = new State(0, vInitial);
            } else {
                var prev = atT(vInitial, t - 1, memo, dv);
                var p = prev.position() + prev.velocity();
                var v = dv.apply(prev.velocity());
                state = new State(p, v);
            }
            memo.put(key, state);
        }
        return state;
    }

    public Result fire(int vxInitial, int vyInitial) {
        var path = new ArrayList<Point2D>();
        State xT, yT;
        for (int t = 0; t < Integer.MAX_VALUE; t++) {
            xT = atT(vxInitial, t, xMemo, ProbeLauncher::nextVx);
            yT = atT(vyInitial, t, yMemo, ProbeLauncher::nextVy);
            var p = new Point2D(xT.position(), yT.position());
            path.add(p);
            if (target.hitBy(p.x(), p.y())) return new Result(true, path);
            if (target.missedBy(p.x(), p.y(), yT.velocity())) return new Result(false, path);
        }
        throw new IllegalStateException("You must have messed something up " + vxInitial + ", " + vyInitial);
    }

    public boolean canHit(int ivx) {
        State state;
        for (int t = 0; t < ivx; t++) {
            state = atT(ivx, t, xMemo, ProbeLauncher::nextVx);
            if (state.position() >= target.xMin() && state.position() <= target.xMax()) return true;
        }
        return false;
    }

    public Set<Integer> possibleInitialVxs() {
        var result = new HashSet<Integer>();
        boolean hitOnce = false;
        for (int ivx = 0;; ivx++) {
            boolean canHit = canHit(ivx);
            if (canHit) {
                result.add(ivx);
                hitOnce = true;
            } else {
                // stop looping if we miss after hitting
                if (hitOnce) break;
            }
        }
        return result;
    }

    public int highestPossibleY() {
        int highestY = 0;
        var possibleVx = this.possibleInitialVxs();
        for (int ivx : possibleVx) {
            for (int ivy = -TRIALS; ivy <= TRIALS; ivy++) {
                var result = fire(ivx, ivy);
                if (result.hit()) {
                    // System.out.printf("%d,%d: %d%n", ivx, ivy, result.yMax());
                    highestY = Math.max(highestY, result.yMax());
                }
            }
        }
        return highestY;
    }

    public void showPath(Result result) {
        int yMin = target.yMin();
        int xMin = target.xMin();
        int yMax = target.yMax();
        int xMax = target.xMax();

        for (var p : result.path()) {
            xMin = Math.min(p.x(), xMin);
            xMax = Math.max(p.x(), xMax);
            yMin = Math.min(p.y(), yMin);
            yMax = Math.max(p.y(), yMax);
        }

        for (int y = yMax; y >= yMin; y--) {
            for (int x = 0; x <= xMax; x++) {
                var p = new Point2D(x, y);
                var s = ".";
                if (p.x() == 0 && p.y() == 0) s = "S";
                else if (result.path().stream().anyMatch(c -> c.equals(p))) s = "#";
                else if (target.hitBy(x, y)) s = "T";
                System.out.print(s);
            }
            System.out.print("\n");
        }
        System.out.println(result.path());
    }

    public int possibleInitialVelocities() {
        Set<Point2D> hits = new HashSet<>();
        for (int ivx = -TRIALS; ivx <= TRIALS; ivx++) {
            for (int ivy = -TRIALS; ivy <= TRIALS; ivy++) {
                var result = fire(ivx, ivy);
                if (result.hit()) { hits.add(new Point2D(ivx, ivy)); }
            }
        }
        return hits.size();
    }
}
