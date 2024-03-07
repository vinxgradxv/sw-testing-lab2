package ru.tinkoff.ps.ops.test.swt;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
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

    private Cos cosWithMockSin;
    private Cos cosWithSpySin;

    @BeforeEach
    void setUp() {
        cosWithMockSin = new Cos(mockSin);
        cosWithSpySin = new Cos(spySin);
    }

    @Test
    @DisplayName("Verify Sin is called at least once with Spy")
    public void callSinWithSpyTest() {
        cosWithSpySin.calculate(6d, 0.001d);
        verify(spySin, atLeastOnce()).calculate(anyDouble(), anyDouble());
    }

    @ParameterizedTest
    @ValueSource(doubles = {0, Math.PI / 2, Math.PI, 3 * Math.PI / 2, 2 * Math.PI})
    @DisplayName("Parameterized test for various values of X")
    public void parameterizedTestForCos(double arg) {
        cosWithSpySin.calculate(arg, 0.001d);
        verify(spySin, times(1)).calculate(anyDouble(), anyDouble());
    }

    @Test
    @DisplayName("Use specific value from Sin")
    public void useSpecificValueFromSinTest() {
        double arg = 5d;
        double correctedArg = Math.PI / 2 + arg;
        double expectedSinResult = 0.283662d;

        when(mockSin.calculate(eq(correctedArg), anyDouble())).thenReturn(expectedSinResult);

        double result = cosWithMockSin.calculate(arg, 0.000001d);
        Assertions.assertEquals(expectedSinResult, result, "The Cos calculation did not return the expected Sin value.");
    }

    @Test
    @DisplayName("Handle null value from Sin")
    public void handleNullValueFromSinTest() {
        double arg = 5d;
        double correctedArg = Math.PI / 2 + arg;

        when(mockSin.calculate(eq(correctedArg), anyDouble())).thenReturn(null);

        Assertions.assertNull(cosWithMockSin.calculate(arg, 0.000001d), "The Cos calculation should return null when Sin returns null.");
    }

    @Test
    @DisplayName("Integration test with real Sin")
    public void integrationTestWithRealSin() {
        Sin realSin = new Sin();
        Cos cos = new Cos(realSin);
        double arg = Math.PI / 6;
        double precision = 0.0001d;

        double expectedCosValue = Math.sqrt(3) / 2;
        double result = cos.calculate(arg, precision);

        Assertions.assertEquals(expectedCosValue, result, 0.01, "The Cos calculation with real Sin did not return the expected value.");
    }
}
