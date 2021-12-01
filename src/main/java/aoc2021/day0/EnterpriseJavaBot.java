package aoc2021.day0;

import java.util.function.Function;

import static aoc2021.Util.wrappedGet;

public class EnterpriseJavaBot {

    enum Heading {
        E(1, 0),
        S(0, -1),
        W(-1, 0),
        N(0, 1);

        private final int dx;
        private final int dy;

        Heading(int dx, int dy) {
            this.dx = dx;
            this.dy = dy;
        }
    }

    record RobotState(int x, int y, Heading heading) {}

    sealed interface Instruction extends Function<RobotState,RobotState> permits Forward, Back, Left, Right, NoOp {}

    record Forward(Integer distance) implements EnterpriseJavaBot.Instruction {
        @Override
        public RobotState apply(RobotState state) {
            var dx = distance * state.heading.dx;
            var dy = distance * state.heading.dy;
            return new RobotState(state.x() + dx, state.y() + dy, state.heading);
        }
    }

    record Back(Integer distance) implements EnterpriseJavaBot.Instruction {
        @Override
        public RobotState apply(RobotState state) {
           return new Forward(-distance).apply(state);
        }
    }

    record Left(Integer times) implements EnterpriseJavaBot.Instruction {
        @Override
        public RobotState apply(RobotState state) {
            return new Right(-times).apply(state);
        }
    }

    record Right(Integer times) implements EnterpriseJavaBot.Instruction {
        @Override
        public RobotState apply(RobotState state) {
            var idx = state.heading().ordinal() + times;
            return new RobotState(state.x(), state.y(), wrappedGet(Heading.values(), idx));
        }
    }

    record NoOp() implements EnterpriseJavaBot.Instruction {
        @Override
        public RobotState apply(RobotState state) {
            return state;
        }
    }

    static Integer getValue(String raw) {
        return Integer.parseInt(raw.substring(1));
    }

    static Instruction parseInstruction(String raw) {
        String first = raw.substring(0, 1);

        return switch(first) {
            case "L" -> new Left(getValue(raw));
            case "R" -> new Right(getValue(raw));
            case "F" -> new Forward(getValue(raw));
            case "B" -> new Back(getValue(raw));
            case "#" -> new NoOp();
            default -> throw new IllegalArgumentException("Unrecognized instruction: '%s'".formatted(raw));
        };
    }

    private RobotState state;

    public EnterpriseJavaBot() {
        this.state = new RobotState(0, 0, Heading.E);
    }

    public void processInstruction(Instruction instruction) {
        System.out.println("> " + instruction);
        this.state = instruction.apply(this.state);
        System.out.println(this.state);
    }

    public void processInstructions(String instructions) {
       instructions.lines().map(EnterpriseJavaBot::parseInstruction).forEach(this::processInstruction);
    }

    static String INSTRUCTIONS = """
            # Secret Robot Instructions
            # Turn Left 3 rotations
            L3
            # Go forward 10
            F10
            # you get the idea...
            B5
            R1
            F3
            R1
            """;

    public static void main(String... args) {
        new EnterpriseJavaBot().processInstructions(INSTRUCTIONS);
    }
}
