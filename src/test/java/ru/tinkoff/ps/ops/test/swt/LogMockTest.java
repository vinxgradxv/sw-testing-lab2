package ru.tinkoff.ps.ops.test.swt;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.tinkoff.ps.ops.test.swt.functions.logariphmic.Ln;
import ru.tinkoff.ps.ops.test.swt.functions.logariphmic.Log;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class LogMockTest extends BaseTest {
    @Mock
    private Ln mockLn;

    @Spy
    private Ln spyLn;

    @Test
    public void callLnLog2Test() {
        var log2 = new Log(2, spyLn);
        log2.calculate(6d, 0.001d);

        verify(spyLn, atLeastOnce()).calculate(any(Double.class), any(Double.class));
    }

    @Test
    public void useValueFromLnLog2Test() {
        var arg = 5d;
        var lnResult = 2d;

        when(mockLn.calculate(eq(arg), any(Double.class))).thenReturn(lnResult);
        when(mockLn.calculate(eq(2d), any(Double.class))).thenReturn(lnResult);

        final Log log2 = new Log(2, mockLn);

        Assertions.assertEquals(1, log2.calculate(arg, 0.000001d));
    }

    @Test
    public void useNullValueFromLnLog2Test() {
        var arg = 5d;

        when(mockLn.calculate(eq(arg), any(Double.class))).thenReturn(null);

        final Log log2 = new Log(2, mockLn);

        Assertions.assertThrows(NullPointerException.class, () -> log2.calculate(arg, 0.000001d));
    }

    @Test
    public void callLnLog10Test() {
        var log10 = new Log(10, spyLn);
        log10.calculate(6d, 0.001d);

        verify(spyLn, atLeastOnce()).calculate(any(Double.class), any(Double.class));
    }

    @Test
    public void useValueFromLnLog10Test() {
        var arg = 5d;
        var lnResult = 2d;

        when(mockLn.calculate(eq(arg), any(Double.class))).thenReturn(lnResult);
        when(mockLn.calculate(eq(10d), any(Double.class))).thenReturn(lnResult);

        final Log log10 = new Log(10, mockLn);

        Assertions.assertEquals(1, log10.calculate(arg, 0.000001d));
    }

    @Test
    public void useNullValueFromLnLog10Test() {
        var arg = 5d;

        when(mockLn.calculate(eq(arg), any(Double.class))).thenReturn(null);

        final Log log10 = new Log(10, mockLn);

        Assertions.assertThrows(NullPointerException.class, () -> log10.calculate(arg, 0.000001d));
    }

}
