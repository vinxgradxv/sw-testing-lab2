package ru.tinkoff.ps.ops.test.swt;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import ru.tinkoff.ps.ops.test.swt.functions.FunctionsSystem;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class FunctionsSystemTest extends BaseTest {

    @Test
    void nullValueTest() {
        final FunctionsSystem system = new FunctionsSystem();
        assertThrows(NullPointerException.class, () -> system.calculate(null, PRECISION));
    }

    @Test
    void nullPrecisionTest() {
        final FunctionsSystem system = new FunctionsSystem();
        assertThrows(NullPointerException.class, () -> system.calculate(-2d, null));
    }

    @Test
    void zeroTest() {
        final FunctionsSystem system = new FunctionsSystem();
        assertEquals(Math.cos(0d), system.calculate(0d, PRECISION), PRECISION);
    }

    @Test
    void oneTest() {
        final FunctionsSystem system = new FunctionsSystem();
        assertThrows(ArithmeticException.class, () -> system.calculate(1d, PRECISION));
    }

    @Test
    void extremeValueTest() {
        final FunctionsSystem system = new FunctionsSystem();
        assertEquals(Double.NaN, system.calculate(Double.MAX_VALUE, PRECISION), "Handling of Double.MAX_VALUE");
    }

    @Test
    void negativeValueTest() {
        final FunctionsSystem system = new FunctionsSystem();
        double negativeX = -Math.PI / 4;
        assertEquals(Math.cos(negativeX), system.calculate(negativeX, PRECISION), PRECISION);
    }

    @ParameterizedTest
    @MethodSource("randomValues")
    void randomValuesTest(Double value, Double expectedResult) {
        final FunctionsSystem system = new FunctionsSystem();
        assertEquals(expectedResult, system.calculate(value, PRECISION), PRECISION);
    }

    private static Stream<Arguments> randomValues() {
        return Stream.of(
                Arguments.of(0.5d, 644.9496926692636d),
                Arguments.of(Math.PI / 2, 646.0944224905096d),
                Arguments.of(Math.PI, 646.7875696251149d),
                Arguments.of(100d, 650.2480099067913d),
                Arguments.of(-0.5d, 0.8775826d),
                Arguments.of(-Math.PI / 2, 0d),
                Arguments.of(-Math.PI, -1d),
                Arguments.of(-100d, 0.86231887d));
    }
}
