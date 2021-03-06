package aoc2021.day8;

import java.util.*;
import java.util.stream.Collectors;

public class SevenSegmentDisplay {
    static final Map<String,Integer> DIGITS = Map.of(
            "abcefg",  0,
            "cf",      1,
            "acdeg",   2,
            "acdfg",   3,
            "bcdf",    4,
            "abdfg",   5,
            "abdefg",  6,
            "acf",     7,
            "abcdefg", 8,
            "abcdfg",  9
    );
    // 0 -> 6 etc
    static final Map<Integer,Integer> COUNTS = DIGITS.entrySet().stream().collect(
            Collectors.toMap(Map.Entry::getValue, e -> e.getKey().length())
    );
    // 6 -> [0, 6, 9] etc
    static final Map<Integer, List<Integer>> DIGITS_BY_COUNT = new HashMap<>();

    static {
        COUNTS.forEach((key, value) -> DIGITS_BY_COUNT.computeIfAbsent(value, k -> new ArrayList<>()).add(key));
    }

    static Optional<Integer> getSingleDigit(String val) {
        var possible = DIGITS_BY_COUNT.get(val.length());
        if (possible.size() == 1) {
            return Optional.of(possible.get(0));
        }
        return Optional.empty();
    }

    public static class SevenSegmentIO {
        private final Map<String,Integer> digits = new HashMap<>();
        private final Map<Integer,String> signals = new HashMap<>();
        private final String[] outputs;

        public SevenSegmentIO(String[] inputs, String[] outputs) {
            this.outputs = outputs;

            Set<String> unknowns = new HashSet<>();
            unknowns.addAll(Arrays.stream(inputs).map(s -> sorted(s)).toList());
            unknowns.addAll(Arrays.stream(outputs).map(s -> sorted(s)).toList());

            // pass 1: find the ones we're sure of
            unknowns.forEach(s -> {
                var matches = DIGITS_BY_COUNT.get(s.length());
                if (matches.size() == 1) remember(matches.get(0), s);
            });
            //System.out.println("First pass " + digits);
            unknowns.removeAll(signals.values());

            // pass 2: use those to find the others
            unknowns.forEach(s -> {
                if (s.length() == 5) {
                    if      (Sets.diff(charset(s), charset(signals.get(1))).size() == 3) remember(3, s);
                    else if (Sets.diff(charset(s), charset(signals.get(4))).size() == 3) remember(2, s);
                    else                                                                 remember(5, s);
                }
                if (s.length() == 6) {
                    if      (Sets.diff(charset(s), charset(signals.get(7))).size() == 4) remember(6, s);
                    else if (Sets.diff(charset(s), charset(signals.get(4))).size() == 3) remember(0, s);
                    else                                                                 remember(9, s);

                }
            });
        }

        private void remember(int digit, String signal) {
                this.signals.put(digit, signal);
                this.digits.put(signal, digit);
            }

        String[] outputs() {
            return this.outputs;
        }

        public int outputValue() {
            int s = 0;
            for (int i = 0; i < 4; i++) {
                s += Math.pow(10, 3 - i) * digitFor(outputs[i]);
            }
            return s;
        }

        int digitFor(String signal) {
            return digits.get(sorted(signal));
        }
    }

    static SevenSegmentIO parseLine(String line) {
        var parts = line.split(" \\| ");
        var inputs = parts[0].split(" ");
        var outputs = parts[1].split(" ");
        return new SevenSegmentIO(inputs, outputs);
    }

    static String sorted(String unsorted) {
        var chars = unsorted.toCharArray();
        Arrays.sort(chars);
        return new String(chars);
    }

    static Set<Character> charset(String s) {
        return s.chars().mapToObj(i -> (char)i).collect(Collectors.toSet());
    }

}
