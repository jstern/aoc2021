package aoc2021.day13;

import aoc2021.day5.Point2D;

import java.util.*;
import java.util.function.BiFunction;

public class MapOfSomeTransparentPaper {
    private Set<Point2D> visible = new HashSet<>();
    private List<Integer> counts = new ArrayList<>();

    public MapOfSomeTransparentPaper(String input) {
        input.lines().forEach(s -> {
            if (s.strip().length() != 0) {
                if (s.startsWith("fold along y=")) {
                    applyFold(MapOfSomeTransparentPaper::foldUp, Integer.parseInt(s.substring(13)));
                    counts.add(visible.size());
                } else if (s.startsWith("fold along x=")) {
                    applyFold(MapOfSomeTransparentPaper::foldLeft, Integer.parseInt(s.substring(13)));
                    counts.add(visible.size());
                } else {
                    visible.add(Point2D.of(s));
                }
            }
        });
    }

    public Integer countAfter(int fold) {
        return counts.get(fold - 1);
    }

    private void applyFold(BiFunction<Point2D, Integer,Optional<Point2D>> fold, int f) {
        var updated = new HashSet<Point2D>();
        visible.forEach(p -> {
            var newP = fold.apply(p, f);
            if (newP.isPresent()) updated.add(newP.get());
        });
        visible = updated;
    }

    static Optional<Point2D> foldLeft(Point2D p, int f) {
        if (p.x() < f) return Optional.of(p);
        if (p.x() == f) return Optional.empty();
        return Optional.of(new Point2D(fold(p.x(), f), p.y()));
    }

    static Optional<Point2D> foldUp(Point2D p, int f) {
        if (p.y() < f) return Optional.of(p);
        if (p.y() == f) return Optional.empty();
        return Optional.of(new Point2D(p.x(), fold(p.y(), f)));
    }

    static int fold(int p, int f) {
        return f - (p - f);
    }

    public String show() {
        var result = new StringBuilder();
        var yMax = visible.stream().mapToInt(Point2D::y).max().getAsInt();
        var xMax = visible.stream().mapToInt(Point2D::x).max().getAsInt();
        for (int y = 0; y <= yMax; y++) {
            for (int x = 0; x <= xMax; x++) {
                if (visible.contains(new Point2D(x, y))) result.append("*");
                else result.append(" ");
            }
            result.append("\n");
        }
        return result.toString();
    }
}
