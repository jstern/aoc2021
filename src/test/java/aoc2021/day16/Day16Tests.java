package aoc2021.day16;


import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.params.provider.Arguments.arguments;

public class Day16Tests {

    static String INPUT = "";

    @Test
    @DisplayName("day 16 part 1 example: literal")
    void literal() {
        var packet = BITS.readerFromHex("D2FE28").readNext();
        assertEquals(6, packet.version());
        assertEquals(6, packet.sumVersions());
        assertEquals(2021, ((BITS.Literal)packet).value());
    }

    @Test
    @DisplayName("day 16 part 1 example: operator type 0")
    void op0() {
        var packet = BITS.readerFromHex("38006F45291200").read();
        assertEquals(1, packet.version());
        assertEquals(9, packet.sumVersions());
    }

    @Test
    @DisplayName("day 16 part 1 example: operator type 1")
    void op1() {
        var packet = BITS.readerFromHex("EE00D40C823060").read();
        System.out.println(packet);
        assertEquals(7, packet.version());
        assertEquals(14, packet.sumVersions());
    }

    private static Stream<Arguments> part2examples() {
        return Stream.of(
                arguments("C200B40A82", 3L),
                arguments("04005AC33890", 54L),
                arguments("880086C3E88112", 7L),
                arguments("CE00C43D881120", 9L),
                arguments("D8005AC2A8F0", 1L),
                arguments("F600BC2D8F", 0L),
                arguments("9C005AC2F8F0", 0L),
                arguments("9C0141080250320F1802104A08", 1L)
        );
    }

    @ParameterizedTest(name = "{0} -> {1}")
    @MethodSource("part2examples")
    void day16part2(String hex, Long value) {
        assertEquals(value, BITS.readerFromHex(hex).read().value());
    }

}
