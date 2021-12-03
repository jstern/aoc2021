package aoc2021.day2;

public class Hoagie {
    public int aim = 0;
    public int depth = 0;
    public int progress = 0;

    enum Direction {forward, down, up}

    public void move(String instruction) {
        //System.out.println(instruction);
        var parts = instruction.split(" ");
        int x = Integer.parseInt(parts[1]);
        switch(Direction.valueOf(parts[0])) {
            case forward -> {
                progress += x;
                depth += aim * x;
            }
            case up -> aim -= x;
            case down -> aim += x;
        }
        //System.out.println("aim %d depth %d progress %d".formatted(aim, depth, progress));
    }
}