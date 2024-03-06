package ru.tinkoff.ps.ops.test.swt;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import ru.tinkoff.ps.ops.test.swt.functions.logariphmic.Log;

import java.util.stream.Stream;

public class LogTest extends BaseTest {
    @Autowired
    @Qualifier("log2")
    private Log log2;

    @Autowired
    @Qualifier("log10")
    private Log log10;

    @Test
    public void testLog2BorderValue() {
        Assertions.assertEquals(0, log2.calculate(1d, PRECISION));
    }

    @Test
    public void testLog10BorderValue() {
        Assertions.assertEquals(0, log10.calculate(1d, PRECISION));
    }

    @ParameterizedTest
    @MethodSource("invalidValues")
    public void testInvalidValueLog2(Double value) {
        Assertions.assertThrows(IllegalArgumentException.class, () -> log2.calculate(value, PRECISION));
    }

    @ParameterizedTest
    @MethodSource("invalidValues")
    public void testInvalidValueLog10(Double value) {
        Assertions.assertThrows(IllegalArgumentException.class, () -> log10.calculate(value, PRECISION));
    }

    @ParameterizedTest
    @MethodSource("less1Values")
    public void testValuesLess1Log2(Double value) {
        Assertions.assertEquals(Math.log(value) / Math.log(2), log2.calculate(value, PRECISION), PRECISION_ASSERT);
    }

    @ParameterizedTest
    @MethodSource("less1Values")
    public void testValuesLess1Log10(Double value) {
        Assertions.assertEquals(Math.log10(value), log10.calculate(value, PRECISION), PRECISION_ASSERT);
    }

    @ParameterizedTest
    @MethodSource("more1Values")
    public void testValuesMore1Log2(Double value) {
        Assertions.assertEquals(Math.log(value) / Math.log(2), log2.calculate(value, PRECISION), PRECISION_ASSERT);
    }

    @ParameterizedTest
    @MethodSource("more1Values")
    public void testValuesMore1Log10(Double value) {
        Assertions.assertEquals(Math.log10(value), log10.calculate(value, PRECISION), PRECISION_ASSERT);
    }

    private static Stream<Arguments> invalidValues() {
        return Stream.of(
                Arguments.of(0d),
                Arguments.of(-1d));
    }

    private static Stream<Arguments> less1Values() {
        return Stream.of(
                Arguments.of(0.1d),
                Arguments.of(0.3d),
                Arguments.of(0.5d),
                Arguments.of(0.7d),
                Arguments.of(0.9d));
    }
    private static Stream<Arguments> more1Values() {
        return Stream.of(
                Arguments.of(1.5d),
                Arguments.of(2d),
                Arguments.of(4d),
                Arguments.of(10d),
                Arguments.of(100d));
    }
}
