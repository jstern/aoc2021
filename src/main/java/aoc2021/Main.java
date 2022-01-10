package aoc2021;

public class Main {
    public static void main(String... args) throws Exception {
        if (args.length == 0) {
            for (int day = 1; day < 26; day++) {
                try {
                    System.out.println();
                    System.out.printf("Day %d Part 1%n", day);
                    Solution.run(String.valueOf(day), "1");
                } catch (Exception e) {
                    System.out.println(e);
                }
                try {
                    System.out.println();
                    System.out.printf("Day %d Part 2%n", day);
                    Solution.run(String.valueOf(day), "2");
                } catch (Exception e) {
                    System.out.println(e);
                }
            }
        } else if (args.length == 1) {
            Solution.run(args[0], "1");
            Solution.run(args[0], "2");
        } else {
            Solution.run(args[0], args[1]);
        }
    }
}
