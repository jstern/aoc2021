package aoc2021.day2;

public class SixFootSub {
    public int depth = 0;
    public int progress = 0;

    enum Direction {forward, down, up}

    public void move(String instruction) {
        var parts = instruction.split(" ");
        int distance = Integer.parseInt(parts[1]);
        switch(Direction.valueOf(parts[0])) {
            case forward -> progress += distance;
            case up -> depth -= distance;
            case down -> depth += distance;
        }
    }
}
