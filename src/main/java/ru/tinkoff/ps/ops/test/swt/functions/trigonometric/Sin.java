package ru.tinkoff.ps.ops.test.swt.functions.trigonometric;

import ru.tinkoff.ps.ops.test.swt.functions.LimitedIterationsExpandableFunction;

public class Sin extends LimitedIterationsExpandableFunction {

    public Sin() {
        super();
    }

    @Override
    public Double calculate(Double x, Double precision) {
        checkValidity(x, precision);
        x = fixPeriod(x);
        double xPow = x;
        x = 1d;

        double result = 0;
        double lastResult;

        double denominator = 1;

        var multiplier1 = 1;
        int i = 1;
        do {
            lastResult = result;
            for (int j = 2 * i - 1; j > Math.max(2 * (i-1) - 1, 0); j--) {
                denominator *= j;
                x *= xPow;
            }

            result += multiplier1 * x / denominator;

            i++;
            multiplier1 *= -1;
        } while (i <= 2 || Math.abs(lastResult - result) >= precision);

        return result;
    }

    private static double fixPeriod(double x) {
        if (x > 0) {
            while (x >= 2 * Math.PI) {
                x -= 2 * Math.PI;
            }
        } else if (x < 0){
            while (x <= 0) {
                x += 2 * Math.PI;
            }
        }
        return x;
    }
}
