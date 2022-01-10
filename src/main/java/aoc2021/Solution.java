package aoc2021;

import java.lang.reflect.Method;

public interface Solution {

    default Object part1(String input) { return ""; }
    default Object part2(String input) { return ""; }

    public static void run(String day, String part) throws Exception {
        // Assume puzzle inputs are always reasonably sized and we can start with
        // the full input string.
        String input = new PuzzleInput("2021", day).asString();

        // Record starting time and memory
        long start = System.currentTimeMillis();
        //long freeInitial = bytesToMB(Runtime.getRuntime().freeMemory());
        //long totalInitial = bytesToMB(Runtime.getRuntime().totalMemory());

        // Find and run the solution
        // Using reflection because I'm too lazy to do it with annotations, DI, etc
        Solution solution = Solution.forDay(day);
        Object result = solution.forPart(part).invoke(solution, input);

        // Record
        long elapsed = System.currentTimeMillis() - start;
        //long freeFinal = bytesToMB(Runtime.getRuntime().freeMemory());
        //long totalFinal = bytesToMB(Runtime.getRuntime().totalMemory());


        System.out.printf("Result in %d ms:%n", elapsed);
        //System.out.printf("Initial memory: %dMB total, %dMB free%n", totalInitial, freeInitial);
        //System.out.printf("Final memory: %dMB total, %dMB free%n", totalFinal, freeFinal);

        System.out.println(result);
    }

    static Solution forDay(String day) throws Exception {
        var cls = Class.forName("aoc2021.day%s.Day%s".formatted(day, day));
        return (Solution)cls.getConstructor().newInstance();
    }

    private Method forPart(String part) {
        for (Method method: this.getClass().getDeclaredMethods()) {
            if (method.getName().equals("part%s".formatted(part))) {
                return method;
            }
        }
        throw new IllegalArgumentException("No method matching part%s in %s".formatted(part, this.getClass().getName()));
    }

    static long bytesToMB(long bytes) {
        return bytes / (1024L * 1024L);
    }
}
