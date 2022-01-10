package aoc2021.day10;

import java.util.*;
import java.util.stream.Collectors;

public class NavigationSubsystem {
    static final Map<Character,Integer> ERROR_VALUES = Map.of(
            ')', 3, ']', 57, '}', 1197, '>', 25137
    );
    static final Map<Character,Integer> COMPLETION_VALUES = Map.of(
            ')', 1, ']', 2, '}', 3, '>', 4
    );
    static final Map<Character,Character> OPENERS = Map.of(
            ')', '(', ']', '[', '}', '{', '>', '<'
    );
    static final Map<Character,Character> CLOSERS = OPENERS.entrySet().stream().collect(
            Collectors.toMap(Map.Entry::getValue, Map.Entry::getKey)
    );

    public static int errorScore(String input) {
        return input.lines().map(NavigationSubsystem::parse).map(ParseResult::errorScore).reduce(0, Integer::sum);
    }

    public static long completionScore(String input) {
        var results = input
                .lines()
                .map(NavigationSubsystem::parse)
                .map(ParseResult::completionScore)
                .filter(r -> r > 0)
                .sorted()
                .toList();
        return results.get(results.size() / 2);

        // 567895273 too low
    }

    static ParseResult parse(String line) {
        var stack = new ArrayDeque<Character>();
        for (char c : line.toCharArray()) {
            var closing = OPENERS.containsKey(c);
            var open = stack.peek();
            if (closing) {
                if   (!OPENERS.get(c).equals(open)) return new ParseResult(c, null);
                else                                stack.pop();
            } else {
                stack.push(c);
            }
        }
        return new ParseResult(null, stack);
    }

    public record ParseResult(Character errorChar, Deque<Character> incomplete) {
        public boolean isComplete() {
            return incomplete.size() == 0;
        }

        public boolean isError() {
            return errorChar != null;
        }

        public int errorScore() {
            return errorChar == null ? 0 : ERROR_VALUES.get(errorChar);
        }

        public long completionScore() {
            if (isError() || isComplete()) return 0;

            long total = 0;
            while(incomplete.size() > 0) {
                var comp = CLOSERS.get(incomplete.pop());
                total = total * 5 + COMPLETION_VALUES.get(comp);
            }
            return total;
        }
    }
}
