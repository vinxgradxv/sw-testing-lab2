package ru.tinkoff.ps.ops.test.swt.functions.logariphmic;

import ru.tinkoff.ps.ops.test.swt.functions.LimitedIterationsExpandableFunction;


public class Ln extends LimitedIterationsExpandableFunction {
    @Override
    public Double calculate(Double x, Double precision) {
        checkValidity(x, precision);
        if (x <= 0) {
            throw new IllegalArgumentException();
        }

        x--;
        double xPow = x;

        double lastResult;
        double result = 0;

        var multiplier1 = 1;
        int i = 1;

        if (xPow <= 1) {
            do {
                lastResult = result;
                result += multiplier1 * x / i;
                x *= xPow;
                i++;
                multiplier1 *= -1;
            } while ((i <= 2 || Math.abs(lastResult - result) >= precision));
        } else {
            do {
                lastResult = result;
                result += multiplier1 / x / i;
                x *= xPow;
                i++;
                multiplier1 *= -1;
            } while ((i <= 2 || Math.abs(lastResult - result) >= precision));
            result += calculate(xPow, precision);
        }

        return result;
    }
}
