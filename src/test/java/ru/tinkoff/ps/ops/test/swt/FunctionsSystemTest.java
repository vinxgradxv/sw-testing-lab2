package ru.tinkoff.ps.ops.test.swt;

import org.junit.jupiter.api.Test;
import ru.tinkoff.ps.ops.test.swt.functions.FunctionsSystem;

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
    void OneTest() {
        final FunctionsSystem system = new FunctionsSystem();
        assertThrows(ArithmeticException.class, () -> system.calculate(1d, PRECISION));
    }
}
