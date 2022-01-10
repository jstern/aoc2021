package aoc2021.day8;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class Sets {
    public static <T> Set<T> diff(Set<T> a, Set<T> b) {
        return a.stream().filter(v -> !b.contains(v)).collect(Collectors.toSet());
    }

    public static <T> Set<T> union(Set<T> a, Set<T> b) {
        var result = new HashSet<T>();
        result.addAll(a);
        result.addAll(b);
        return result;
    }

    public static <T> Set<T> intersection(Set<T> a, Set<T> b) {
        return a.stream().filter(b::contains).collect(Collectors.toSet());
    }
}
