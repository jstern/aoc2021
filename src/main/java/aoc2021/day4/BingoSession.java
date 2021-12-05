package aoc2021.day4;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class BingoSession {

    enum Strategy {WIN, LET_THE_SQUID_WIN};

    static int findAndScoreWinner(List<String> input) {
        return findAndScoreWinner(input, Strategy.WIN);
    }

    static int findAndScoreWinner(List<String> input, Strategy strategy) {
        var boards = new ArrayList<BingoBoard>();
        BingoBoard board = null;
        List<Integer> numbers = new ArrayList<>();
        Map<Integer,Set<Integer>> boardsWithNumber = new HashMap<>();
        Set<Integer> boardsInPlay = new HashSet<>();
        int boardNum = 0;

        for (String line : input) {
            if (line.isBlank()) {
                if (null == board) {
                    board = new BingoBoard();
                    continue;
                }

                boards.add(board);
                for (int num : board.allNumbers()) {
                    boardsWithNumber.computeIfAbsent(num, k -> new HashSet<>()).add(boardNum);
                }

                board = new BingoBoard();
                boardNum++;
            }
            else if(line.contains(",")) {
                for (String s : line.split(",")) {
                    if (!s.isEmpty()) numbers.add(Integer.parseInt(s));
                }
            }
            else {
                board.addRow(line);
            }
        }
        boards.add(board);
        for (int num : board.allNumbers()) {
            boardsWithNumber.computeIfAbsent(num, k -> new HashSet<>()).add(boardNum);
        }
        boardsInPlay.addAll(IntStream.rangeClosed(0, boardNum).boxed().collect(Collectors.toList()));

        for (Integer number : numbers) {
            System.out.println("The next number is... " + number);
            var matchingBoards = boardsWithNumber.get(number);
            for (var b : matchingBoards) {
                if (!boardsInPlay.contains(b)) continue;
                try {
                    boards.get(b).play(number);
                } catch(Bingo bingo) {
                    if (strategy == Strategy.WIN) {
                        System.out.println("BINGO!!!! Board " + b);
                        System.out.println(boards.get(b));
                        return boards.get(b).score(number);
                    } else {
                        if (boardsInPlay.size() == 1) {
                            System.out.println("YES!!! SQUID GAME !!! Board " + b);
                            System.out.println(boards.get(b));
                            return boards.get(b).score(number);
                        }
                        System.out.println("Shhh... let's pretend board %d didn't bingo".formatted(b));
                        boardsInPlay.remove(b);
                    }
                }
            }
            //for (BingoBoard b : boards) {
            //    System.out.println(b);
            //}
        }

        throw new IllegalStateException("Nobody won!");
    }

    public static class BingoBoard {
        private final List<Integer> values = new ArrayList<>();
        private final Map<Integer,Integer> locations = new HashMap<>();
        private final Set<Integer> covered = new HashSet<>();

        void addRow(String row) {
            String[] vals = row.split("\\s+");
            Arrays.stream(vals).filter(v -> !v.isEmpty()).map(Integer::parseInt).forEach(i -> {
                values.add(i);
                locations.put(i, values.size() - 1);
            });
        }

        static int to1D(int row, int col) {
            return row * 5 + col;
        }

        Collection<Integer> allNumbers() {
            return values;
        }

        void play(int number) throws Bingo {
            Integer loc = locations.get(number);
            assert values.get(loc) == number;
            if (null == loc) { return; }
            covered.add(loc);
            checkBingo(loc);
        }

        void checkBingo(int loc) throws Bingo {
            if (checkCovered(row(loc)) || checkCovered(col(loc))) {
                throw new Bingo();
            }
        }

        /**
         * locations in same row as loc
         */
       Collection<Integer> row(int loc) {
           int first = 5 * (loc / 5);
           var result = new ArrayList<Integer>();
           for (int i = first; i < first + 5; i++) {
               result.add(i);
           }
           return result;
       }

        /**
         * locations in same row as col
         */
        Collection<Integer> col(int loc) {
            int first = loc % 5;
            var result = new ArrayList<Integer>();
            for (int i = first; i < first + 21; i += 5) {
                result.add(i);
            }
            return result;
        }

        boolean checkCovered(Collection<Integer> locs) {
            return covered.containsAll(locs);
        }

        int score(int number) {
            return number *
                    locations
                            .entrySet()
                            .stream()
                            .filter(e -> !covered.contains(e.getValue()))
                            .map(Map.Entry::getKey)
                            .mapToInt(i -> i)
                            .sum();
        }

        public String toString() {
            var result = new StringBuilder();
            for (int r = 0; r < 5; r++) {
                for (int c = 0; c < 5; c++) {
                    result.append(repr(to1D(r, c)));
                    result.append(" ");
                }
                result.append("\n");
            }
            return result.toString();
        }

        private String repr(int loc) {
            if (covered.contains(loc)) {
                return "\033[0;36m%2d\033[0m".formatted(values.get(loc));
            } else {
                return "%2d".formatted(values.get(loc));
            }
        }
    }

    // throwing an exception is like shouting BINGO!, right?
    static class Bingo extends Throwable {}
}
