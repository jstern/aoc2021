package aoc2021.day5;

public record Point2D(int x, int y) {
    public static Point2D of(String desc) {
        var parts = desc.split(",");
        return new Point2D(Integer.parseInt(parts[0]), Integer.parseInt(parts[1]));
    }

    public Point2D next(int dx, int dy) {
        return new Point2D(x() + dx, y + dy);
    }
}
