package aoc2021;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;
import org.junit.jupiter.params.provider.ArgumentsSource;

import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

import static aoc2021.Util.*;

public class UtilTests {

    static final Integer[] ARRAY = {0, 1, 2, 3};
    static final List<Integer> LIST = List.of(0, 1, 2, 3);

    static record Example(int idx, int expected) {}

    static class ArgsProvider implements ArgumentsProvider {

        @Override
        public Stream<? extends Arguments> provideArguments(ExtensionContext extensionContext) throws Exception {
            return Stream.of(
                    Arguments.of(new Example(0, 0)),
                    Arguments.of(new Example(5, 1)),
                    Arguments.of(new Example(10, 2)),
                    Arguments.of(new Example(-1, 3)),
                    Arguments.of(new Example(-6, 2))
            );
        }
    }

    @ParameterizedTest
    @ArgumentsSource(ArgsProvider.class)
    @DisplayName("wrappedGet behavior: arrays")
    void arrays(Example ex)  {
        assertEquals(ex.expected, wrappedGet(ARRAY, ex.idx));
    }

    @ParameterizedTest
    @ArgumentsSource(ArgsProvider.class)
    @DisplayName("wrappedGet behavior: lists")
    void lists(Example ex) {
        assertEquals(ex.expected, wrappedGet(LIST, ex.idx));
    }
}
