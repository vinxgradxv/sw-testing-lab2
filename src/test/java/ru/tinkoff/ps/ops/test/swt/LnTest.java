package ru.tinkoff.ps.ops.test.swt;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import ru.tinkoff.ps.ops.test.swt.functions.logariphmic.Ln;

import java.util.stream.Stream;

public class LnTest extends BaseTest {

    @Autowired
    private Ln ln;

    @ParameterizedTest()
    @MethodSource("illegalValues")
    public void functionNotDefinedTest(double x) {
        Assertions.assertThrows(IllegalArgumentException.class, () -> ln.calculate(x, PRECISION));
    }

    @ParameterizedTest()
    @MethodSource("lessThen1Values")
    public void lessThen1ValuesTest(double x) {
        Assertions.assertEquals(Math.log(x), ln.calculate(x, PRECISION), PRECISION_ASSERT);
    }

    @ParameterizedTest()
    @MethodSource("moreThen1Values")
    public void moreThen1ValuesTest(double x) {
        Assertions.assertEquals(Math.log(x), ln.calculate(x, PRECISION), PRECISION_ASSERT);
    }

    @ParameterizedTest()
    @MethodSource("valuesCloseTo1")
    public void valuesCloseTo1Test(double x) {
        Assertions.assertEquals(Math.log(x), ln.calculate(x, PRECISION), PRECISION_ASSERT, "Ln calculation for values close to 1 should be precise");
    }

    @ParameterizedTest()
    @MethodSource("verySmallPositiveValues")
    public void verySmallPositiveValuesTest(double x) {
        Assertions.assertTrue(Double.isInfinite(ln.calculate(x, PRECISION)), "Ln calculation for very small positive values should be -Infinity");
    }

    @ParameterizedTest()
    @MethodSource("largeValues")
    public void largeValuesAsymptoticBehaviorTest(double x) {
        Assertions.assertFalse(Double.isNaN(ln.calculate(x, PRECISION)), "Ln calculation for large values should not be NaN");
    }

    private static Stream<Arguments> illegalValues() {
        return Stream.of(
                Arguments.of(-1),
                Arguments.of(0));
    }

    private static Stream<Arguments> lessThen1Values() {
        return Stream.of(
                Arguments.of(1),
                Arguments.of(0.1),
                Arguments.of(0.3),
                Arguments.of(0.5),
                Arguments.of(0.7),
                Arguments.of(0.9));
    }

    private static Stream<Arguments> moreThen1Values() {
        return Stream.of(
                Arguments.of(2),
                Arguments.of(Math.E),
                Arguments.of(5),
                Arguments.of(10),
                Arguments.of(100),
                Arguments.of(1000));
    }

    private static Stream<Arguments> valuesCloseTo1() {
        return Stream.of(
                Arguments.of(0.99),
                Arguments.of(1.01),
                Arguments.of(1.00001),
                Arguments.of(0.99999));
    }

    private static Stream<Arguments> verySmallPositiveValues() {
        return Stream.of(
                Arguments.of(Double.MIN_VALUE));
    }

    private static Stream<Arguments> largeValues() {
        return Stream.of(
                Arguments.of(Double.MAX_VALUE),
                Arguments.of(1e308));
    }
}
