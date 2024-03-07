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

    @ParameterizedTest
    @MethodSource("borderValuesForFunctions")
    public void testBorderValues(SeriesExpandableFunction function, Double input, Double precision) {
        Assertions.assertDoesNotThrow(() -> function.calculate(input, precision));
    }

    @ParameterizedTest
    @MethodSource("specificValuesForFunctions")
    public void testSpecificValues(SeriesExpandableFunction function, Double input, Double expected, Double precision) {
        Assertions.assertEquals(expected, function.calculate(input, precision), precision);
    }

    private static Stream<Arguments> functions() {
        return Stream.of(
                Arguments.of(new Sin()),
                Arguments.of(new Cos()),
                Arguments.of(new Ln()),
                Arguments.of(new Log(2)),
                Arguments.of(new Log(10)));
    }

    private static Stream<Arguments> borderValuesForFunctions() {
        return Stream.of(
                Arguments.of(new Sin(), Double.MAX_VALUE, 0.0001),
                Arguments.of(new Cos(), Double.MIN_VALUE, 0.0001),
                Arguments.of(new Ln(), 0.0000001, 0.0001),
                Arguments.of(new Log(2), Double.MAX_VALUE, 0.0001),
                Arguments.of(new Log(10), Double.MIN_VALUE, 0.0001));
    }

    private static Stream<Arguments> specificValuesForFunctions() {
        return Stream.of(
                Arguments.of(new Sin(), Math.PI / 2, 1.0, 0.0001),
                Arguments.of(new Cos(), Math.PI, -1.0, 0.0001),
                Arguments.of(new Ln(), Math.E, 1.0, 0.0001),
                Arguments.of(new Log(2), 2.0, 1.0, 0.0001),
                Arguments.of(new Log(10), 10.0, 1.0, 0.0001));
    }
}
