package ru.tinkoff.ps.ops.test.swt;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.tinkoff.ps.ops.test.swt.functions.trigonometric.Cos;
import ru.tinkoff.ps.ops.test.swt.functions.trigonometric.Sin;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CosMockTest extends BaseTest{

    @Mock
    private Sin mockSin;

    @Spy
    private Sin spySin;

    @Test
    public void callSinTest() {
        final Cos cos = new Cos(spySin);
        cos.calculate(6d, 0.001d);

        verify(spySin, atLeastOnce()).calculate(any(Double.class), any(Double.class));
    }

    @Test
    public void useValueFromSinTest() {
        var arg = 5d;
        var correctedArg = Math.PI / 2 + arg;
        var sinResult = 0.283662d;

        when(mockSin.calculate(eq(correctedArg), any(Double.class))).thenReturn(sinResult);

        final Cos cos = new Cos(mockSin);

        Assertions.assertEquals(sinResult, cos.calculate(arg, 0.000001d));
    }

    @Test
    public void useNullValueFromSinTest() {
        var arg = 5d;
        var correctedArg = Math.PI / 2 + arg;

        when(mockSin.calculate(eq(correctedArg), any(Double.class))).thenReturn(null);

        final Cos cos = new Cos(mockSin);

        Assertions.assertNull(cos.calculate(arg, 0.000001d));
    }
}
