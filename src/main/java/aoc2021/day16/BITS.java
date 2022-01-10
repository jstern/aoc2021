package aoc2021.day16;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class BITS {

    interface Packet {
        long version();
        long sumVersions();
        long value();
    }

    public record Literal(long version, long value) implements Packet {
        @Override
        public long sumVersions() {
            return version();
        }

        public String toString() {
            return "%d:%d".formatted(version, value);
        }
    }

    public record Operator(long version, long type, List<Packet> subpackets, long count, long end) implements Packet {
        @Override
        public long sumVersions() {
            return version() + subpackets().stream().map(Packet::sumVersions).mapToLong(Long::longValue).sum();
        }

        public String toString() {
            return "%d:%d:%d:%s".formatted(version, count, end, subpackets);
        }

        public boolean done(int position) {
            if (count() != 0 && subpackets.size() >= count()) return true;
            return end() != 0 && position >= end();
        }

        public long value() {
            return switch((int) type) {
                case 0 -> subpackets.stream().mapToLong(Packet::value).sum();
                case 1 -> subpackets.stream().mapToLong(Packet::value).reduce(1, (x, y) -> x * y);
                case 2 -> subpackets.stream().mapToLong(Packet::value).min().getAsLong();
                case 3 -> subpackets.stream().mapToLong(Packet::value).max().getAsLong();
                case 5 -> subpackets.get(0).value() > subpackets.get(1).value() ? 1 : 0;
                case 6 -> subpackets.get(0).value() < subpackets.get(1).value() ? 1 : 0;
                case 7 -> subpackets.get(0).value() == subpackets.get(1).value() ? 1 : 0;
                default -> throw new IllegalStateException("Bad operator type " + type);
            };
        }
    }

    public static Reader readerFromHex(String hex) {
        var bin = new StringBuilder();
        for (int i = 0; i < hex.length(); i++) {
            var asBinary = "%4s"
                    .formatted(Integer.toBinaryString(Integer.parseInt(hex.substring(i, i+1), 16)))
                    .replace(" ", "0");
           bin.append(asBinary);
        }
        return new Reader(bin.toString());
    }

    static class NotEnoughData extends RuntimeException {}

    public static class Reader {
        private final String data;
        private final Stack<Operator> stack = new Stack<>();
        private int position = 0;

        public Reader(String data) {
            this.data = data;
        }

        public Packet read() {
            // Get things rolling
            readNext();

            while (!stack.empty()) {
                // we should always have a parent operator if we're reading a full transmission
                var parent = stack.peek();

                try {
                    // Add next packet to parent operator
                    var next = readNext();
                    parent.subpackets.add(next);

                    while (stack.peek().done(position)) {
                        parent = stack.pop();
                        if (stack.empty()) {
                            return parent;  // we've popped the last one
                        }
                    }
                } catch (NotEnoughData ned) {
                    System.out.println("Terminating due to not enough data");
                    return parent;
                }
            }

            return null;
        }

        public Packet readNext() {
            Packet result;

            if (position + 11 <= data.length()) { // if not, we don't have enough runway
                var version = readInt(data, position, position + 3);
                var typeId = readInt(data, position + 3, position + 6);
                position += 6;

                // read a literal and add it as a child of the current operator
                if (typeId == 4) {
                    result = readLiteral(version);
                } else {
                    result = readOperator(version, typeId);
                }
            } else {
                System.out.println("Do we ever get this exception?");
                throw new NotEnoughData();
            }

            //show();
            return result;
        }

        private Literal readLiteral(long version) {
            var repr = new StringBuilder();
            int start = position;
            while (start >= 0) {
                repr.append(data.substring(position + 1, position + 5));
                start = data.charAt(position) == '0' ? -1 : start + 5;
                position += 5;
            }
            return new Literal(version, readInt(repr.toString(), 0, repr.length()));
        }

        private Operator readOperator(long version, long type) {
            var lengthTypeId = data.charAt(position);
            position++;

            Operator operator;
            if (lengthTypeId == '0') {
                var length = readInt(data, position, position + 15);
                position += 15;
                operator = new Operator(version, type, new ArrayList<>(), 0, position + length);
            } else if (lengthTypeId == '1') {
                var count = readInt(data, position, position + 11);
                position += 11;
                operator = new Operator(version, type, new ArrayList<>(), count, 0);
            } else {
                throw new RuntimeException("Invalid lengthTypeId " + lengthTypeId);
            }
            stack.push(operator);
            return operator;
        }

        private void show() {
            System.out.println(data);
            for (int i = 0; i < position; i++) {
                System.out.print(" ");
            }
            System.out.print("|");
            for (int i = position; i < data.length(); i++) {
                System.out.print(" ");
            }
            System.out.print("\n");
        }
    }

    static long readInt(String s, int start, int end) {
        return Long.parseLong(s.substring(start, end), 2);
    }

}