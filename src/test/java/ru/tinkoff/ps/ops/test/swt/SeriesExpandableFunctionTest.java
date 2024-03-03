package ru.tinkoff.ps.ops.test.swt;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import ru.tinkoff.ps.ops.test.swt.functions.SeriesExpandableFunction;
import ru.tinkoff.ps.ops.test.swt.functions.logariphmic.Ln;
import ru.tinkoff.ps.ops.test.swt.functions.logariphmic.Log;
import ru.tinkoff.ps.ops.test.swt.functions.trigonometric.Cos;
import ru.tinkoff.ps.ops.test.swt.functions.trigonometric.Sin;

import java.util.stream.Stream;

public class SeriesExpandableFunctionTest extends BaseTest{
    @ParameterizedTest
    @MethodSource("functions")
    public void testIllegalValue(SeriesExpandableFunction function) {
        Assertions.assertThrows(NullPointerException.class, () -> function.calculate(null, PRECISION));
    }

    @ParameterizedTest
    @MethodSource("functions")
    public void testIllegalPrecision(SeriesExpandableFunction function) {
        Double x = 1d;
        Assertions.assertThrows(NullPointerException.class, () -> function.calculate(x, null));
        Assertions.assertThrows(IllegalArgumentException.class, () -> function.calculate(x, 1d));
        Assertions.assertThrows(IllegalArgumentException.class, () -> function.calculate(x, 0d));
        Assertions.assertThrows(IllegalArgumentException.class, () -> function.calculate(x, 2d));
        Assertions.assertThrows(IllegalArgumentException.class, () -> function.calculate(x, -1d));
    }

    @ParameterizedTest
    @MethodSource("functions")
    public void testCorrectValues(SeriesExpandableFunction function) {
        Double x = 1d;
        Assertions.assertDoesNotThrow(() -> function.calculate(x, 0.0001));
        Assertions.assertDoesNotThrow(() -> function.calculate(x, 0.9999));
    }

    private static Stream<Arguments> functions() {
        return Stream.of(
                Arguments.of(new Sin()),
                Arguments.of(new Cos()),
                Arguments.of(new Ln()),
                Arguments.of(new Log(2)),
                Arguments.of(new Log(10)));
    }
}
