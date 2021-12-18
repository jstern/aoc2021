package aoc2021.day12;

import aoc2021.day8.Sets;

import org.junit.jupiter.api.Test;

import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Day12Tests {

    static String INPUT = """
            start-A
            start-b
            A-c
            A-b
            b-d
            A-end
            b-end""";

    @Test
    void traversal() {
        var caves = new CaveMap(INPUT);
        var paths = caves.explore();
        assertEquals(10, paths.size());
    }

    @Test
    void laxTraversal() {
        var expected = """
                start,A,b,A,b,A,c,A,end
                start,A,b,A,b,A,end
                start,A,b,A,b,end
                start,A,b,A,c,A,b,A,end
                start,A,b,A,c,A,b,end
                start,A,b,A,c,A,c,A,end
                start,A,b,A,c,A,end
                start,A,b,A,end
                start,A,b,d,b,A,c,A,end
                start,A,b,d,b,A,end
                start,A,b,d,b,end
                start,A,b,end
                start,A,c,A,b,A,b,A,end
                start,A,c,A,b,A,b,end
                start,A,c,A,b,A,c,A,end
                start,A,c,A,b,A,end
                start,A,c,A,b,d,b,A,end
                start,A,c,A,b,d,b,end
                start,A,c,A,b,end
                start,A,c,A,c,A,b,A,end
                start,A,c,A,c,A,b,end
                start,A,c,A,c,A,end
                start,A,c,A,end
                start,A,end
                start,b,A,b,A,c,A,end
                start,b,A,b,A,end
                start,b,A,b,end
                start,b,A,c,A,b,A,end
                start,b,A,c,A,b,end
                start,b,A,c,A,c,A,end
                start,b,A,c,A,end
                start,b,A,end
                start,b,d,b,A,c,A,end
                start,b,d,b,A,end
                start,b,d,b,end
                start,b,end""".lines().collect(Collectors.toSet());

        var paths = new CaveMap(INPUT, true).explore();
        System.out.println("MISSING");
        Sets.diff(expected, paths).stream().forEach(System.out::println);
        System.out.println("EXTRA");
        Sets.diff(paths, expected).stream().forEach(System.out::println);
        assertEquals(expected, paths);
    }

}
