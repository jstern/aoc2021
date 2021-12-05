package aoc2021.day4;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Day4Tests {

    static String INPUT = """
            7,4,9,5,11,17,23,2,0,14,21,24,10,16,13,6,15,25,12,22,18,20,8,19,3,26,1
                        
            22 13 17 11  0
             8  2 23  4 24
            21  9 14 16  7
             6 10  3 18  5
             1 12 20 15 19
                        
             3 15  0  2 22
             9 18 13 17  5
            19  8  7 25 23
            20 11 10 24  4
            14 21 16 12  6
                        
            14 21 17 24  4
            10 16 15  9 19
            18  8 23 26 20
            22 11 13  6  5
             2  0 12  3  7""";

    static BingoSession.BingoBoard BOARD = new BingoSession.BingoBoard();
    static {
        BOARD.addRow("14 21 17 24 4");
        BOARD.addRow("10 16 15 9 19");
        BOARD.addRow("18 8 23 26 20");
        BOARD.addRow("22 11 13 6 5");
        BOARD.addRow("2 0 12 3 7");
    }

    @Test
    @DisplayName("day 4 part 1 example")
    void partOneExample() {
        int actual = BingoSession.findAndScoreWinner(INPUT.lines().collect(Collectors.toList()));
        assertEquals(4512, actual);
    }

    @Test
    @DisplayName("day 4 part 2 example")
    void partTwoExample() {
        int actual = BingoSession.findAndScoreWinner(INPUT.lines().collect(Collectors.toList()), BingoSession.Strategy.LET_THE_SQUID_WIN);
        assertEquals(1924, actual);
    }

    @Test
    @DisplayName("board: row locations")
    void boardRow() {
        var first = List.of(0, 1, 2, 3, 4);
        var second = List.of(5, 6, 7, 8, 9);
        assertEquals(first, BOARD.row(0));
        assertEquals(first, BOARD.row(3));
        assertEquals(second, BOARD.row(5));
        assertEquals(second, BOARD.row(7));
    }

    @Test
    @DisplayName("board: col locations")
    void boardCol() {
        var first = List.of(0, 5, 10, 15, 20);
        var second = List.of(1, 6, 11, 16, 21);
        assertEquals(first, BOARD.col(0));
        assertEquals(first, BOARD.col(10));
        assertEquals(second, BOARD.col(1));
        assertEquals(second, BOARD.col(16));
    }
}
