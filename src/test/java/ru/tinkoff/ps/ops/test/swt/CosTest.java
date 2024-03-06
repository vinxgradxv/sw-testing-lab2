package ru.tinkoff.ps.ops.test.swt;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import ru.tinkoff.ps.ops.test.swt.functions.trigonometric.Cos;

import java.util.stream.Stream;

public class CosTest extends BaseTest {
    @Autowired
    private Cos cos;

    @ParameterizedTest
    @MethodSource("borderValues")
    public void testBorderValues(double x) {
        Assertions.assertEquals(1, cos.calculate(x, PRECISION), PRECISION);
    }

    @ParameterizedTest
    @MethodSource("insidePeriodSegmentValues")
    public void testInsidePeriodSegment(double x) {
        Assertions.assertEquals(Math.cos(x), cos.calculate(x, PRECISION), PRECISION);
    }

    @ParameterizedTest
    @MethodSource("outsidePeriodSegmentValues")
    public void testOutsidePeriodSegment(double x) {
        Assertions.assertEquals(Math.cos(x), cos.calculate(x, PRECISION), PRECISION);
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
}
