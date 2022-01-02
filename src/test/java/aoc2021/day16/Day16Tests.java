package aoc2021.day16;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class Day16Tests {

    static String INPUT = "";

    @Test
    @DisplayName("day 16 part 1 example: literal")
    void literal() {
        var packet = BITS.readerFromHex("D2FE28").readNext();
        assertTrue(packet.isLiteral());
        assertEquals(6, packet.version());
        assertEquals(6, packet.sumVersions());
        assertEquals(2021, ((BITS.Literal)packet).value());
    }

    @Test
    @DisplayName("day 16 part 1 example: operator type 0")
    void op0() {
        var packet = BITS.readerFromHex("38006F45291200").read();
        assertFalse(packet.isLiteral());
        assertEquals(1, packet.version());
        assertEquals(9, packet.sumVersions());
    }

    @Test
    @DisplayName("day 16 part 1 example: operator type 1")
    void op1() {
        var packet = BITS.readerFromHex("EE00D40C823060").read();
        System.out.println(packet);
        assertFalse(packet.isLiteral());
        assertEquals(7, packet.version());
        assertEquals(14, packet.sumVersions());
    }

    @Test
    @DisplayName("day 16 part 2 example")
    @Disabled
    void day16part2() {
        assertEquals(true, false);
    }

}
