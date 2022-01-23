package aoc2021.day18;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class Day18Tests {

    static String INPUT = """
            [[[0,[5,8]],[[1,7],[9,6]]],[[4,[1,2]],[[1,4],2]]]
            [[[5,[2,8]],4],[5,[[9,9],0]]]
            [6,[[[6,2],[5,6]],[[7,6],[4,7]]]]
            [[[6,[0,7]],[0,9]],[4,[9,[9,0]]]]
            [[[7,[6,4]],[3,[1,3]]],[[[5,5],1],9]]
            [[6,[[7,3],[3,2]]],[[[3,8],[5,7]],4]]
            [[[[5,4],[7,7]],8],[[8,3],8]]
            [[9,3],[[9,9],[6,[4,9]]]]
            [[2,[[7,7],7]],[[5,8],[[9,3],[0,2]]]]
            [[[[5,2],5],[8,[3,7]]],[[5,[7,5]],[4,4]]]""";

    @Test
    @DisplayName("basic addition")
    void addition1() {
        var a = SnailfishNumber.parse("[1,2]");
        var b = SnailfishNumber.parse("[3,4]");
        assertEquals(SnailfishNumber.parse("[[1,2],[3,4]]"), a.add(b));
    }

    @Test
    @DisplayName("addition with reduction")
    @Disabled
    void addition2() {
        var a = SnailfishNumber.parse("[[[[4,3],4],4],[7,[[8,4],9]]]");
        var b = SnailfishNumber.parse("[1,1]");
        var e = SnailfishNumber.parse("[[[[0,7],4],[[7,8],[6,0]]],[8,1]]");
        assertEquals(e, a.add(b));
    }

    @Test
    @DisplayName("day 18 part 1 example")
    @Disabled
    void day18part1() {
        assertEquals(true, false);
    }

    @Test
    @DisplayName("day 18 part 2 example")
    @Disabled
    void day18part2() {
        assertEquals(true, false);
    }

}