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
}
