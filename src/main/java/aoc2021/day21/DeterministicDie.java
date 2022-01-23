package aoc2021.day21;

public class DeterministicDie implements Die {
    int next = 0;

    public int roll() {
        int result = next + 1;
        next = result % 100;
        return result;
    }
}
