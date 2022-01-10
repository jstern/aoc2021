package aoc2021.day6;

import java.util.Arrays;
import java.util.stream.Collectors;

public class LanternfishPopulation {
    private long[] cohorts = new long[9];

    public LanternfishPopulation(String input) {
        var fish = Arrays.stream(input.split(",")).map(String::strip).collect(Collectors.toList());
        for (String s : fish) {
            int cohort = Integer.parseInt(s);
            cohorts[cohort]++;
        }
    }

    public void simulate(int days) {
        for (int i = 0; i < days; i++) {
            simulateOne();
        }
        //show();
    }

    public void simulateOne() {
        var updated = new long[cohorts.length];

        // After another day (the day after timer becomes 0), its internal timer would reset to 6,
        // and it would create a new lanternfish with an internal timer of 8.
        updated[8] = cohorts[0];
        updated[6] = cohorts[0];

        // special case for juvenile fish
        updated[7] = cohorts[8];

        for (int i = 1; i <= 7; i++) {
            updated[i - 1] += cohorts[i];
        }

        this.cohorts = updated;
    }

    public long size() {
        return Arrays.stream(cohorts).sum();
    }

    public void show() {
        for (int i = 0; i < cohorts.length; i++) {
            System.out.printf("%d:%d ", i, cohorts[i]);
        }
        System.out.println("\nTotal Population: " + size());
    }
}
