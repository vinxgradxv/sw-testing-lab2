package ru.tinkoff.ps.ops.test.swt;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import ru.tinkoff.ps.ops.test.swt.functions.trigonometric.Sin;

import java.util.stream.Stream;

public class SinTest extends BaseTest{

    @Autowired
    private Sin sin;

    @ParameterizedTest
    @MethodSource("borderValues")
    public void testBorderValues(double x) {
        Assertions.assertEquals(0, sin.calculate(x, PRECISION), PRECISION);
    }

    @ParameterizedTest
    @MethodSource("insidePeriodSegmentValues")
    public void testInsidePeriodSegment(double x) {
        Assertions.assertEquals(Math.sin(x), sin.calculate(x, PRECISION), PRECISION);
    }

    @ParameterizedTest
    @MethodSource("outsidePeriodSegmentValues")
    public void testOutsidePeriodSegment(double x) {
        Assertions.assertEquals(Math.sin(x), sin.calculate(x, PRECISION), PRECISION);
    }

    @ParameterizedTest
    @MethodSource("criticalPointsValues")
    public void testCriticalPoints(double x, double expected) {
        Assertions.assertEquals(expected, sin.calculate(x, PRECISION), PRECISION);
    }

    @ParameterizedTest
    @MethodSource("symmetryValues")
    public void testSymmetry(double x) {
        Assertions.assertEquals(sin.calculate(-x, PRECISION), -sin.calculate(x, PRECISION), PRECISION);
    }

    private static Stream<Arguments> borderValues() {
        return Stream.of(
                Arguments.of(0),
                Arguments.of(2 * Math.PI));
    }

    private static Stream<Arguments> insidePeriodSegmentValues() {
        return Stream.of(
                Arguments.of(0.5),
                Arguments.of(Math.PI / 2),
                Arguments.of(Math.PI),
                Arguments.of(3 * Math.PI / 2));
    }

    private static Stream<Arguments> outsidePeriodSegmentValues() {
        return Stream.of(
                Arguments.of(-10 * Math.PI),
                Arguments.of(-1),
                Arguments.of(7),
                Arguments.of(100 * Math.PI + 1));
    }

    private static Stream<Arguments> criticalPointsValues() {
        return Stream.of(
                Arguments.of(Math.PI / 2, 1.0),
                Arguments.of(-Math.PI / 2, -1.0),
                Arguments.of(Math.PI, 0.0),
                Arguments.of(3 * Math.PI / 2, -1.0),
                Arguments.of(2 * Math.PI, 0.0),
                Arguments.of(-Math.PI, 0.0));
    }

    private static Stream<Double> symmetryValues() {
        return Stream.of(-Math.PI / 2, -Math.PI / 4, 0.0, Math.PI / 4, Math.PI / 2);
    }
}
