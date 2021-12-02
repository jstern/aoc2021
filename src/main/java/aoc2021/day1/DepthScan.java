package aoc2021.day1;

public class DepthScan {
    private final int[] measurements;

    DepthScan(String input) {
        this.measurements = input.lines().mapToInt(Integer::parseInt).toArray();
    }

    int countChanges(int windowSize) {
        int incs = 0;
        Integer last = null;

        int start = windowSize - 1;

        for (int i = start; i < measurements.length; i++) {
            int sum = 0;
            for (int j = i; j > i - windowSize; j--) {
                // System.out.println("%d + [%d] %d".formatted(sum, j, measurements[j]));
                sum += measurements[j];
            }
            // System.out.println(sum);
            if (last != null && sum > last) {
                incs++;
                // System.out.println(incs);
            }
            last = sum;
        }
        return incs;
    }
}
