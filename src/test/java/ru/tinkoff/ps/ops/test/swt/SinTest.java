package ru.tinkoff.ps.ops.test.swt;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import ru.tinkoff.ps.ops.test.swt.functions.trigonometric.Sin;

public class SinTest extends BaseTest{

    @Autowired
    private Sin sin;

    @Test
    public void testBorderValues() {
        Assertions.assertEquals(0, sin.calculate(0, 0.0001));
    }
}
