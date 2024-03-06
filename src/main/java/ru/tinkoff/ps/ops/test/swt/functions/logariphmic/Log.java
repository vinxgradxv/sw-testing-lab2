package ru.tinkoff.ps.ops.test.swt.functions.logariphmic;

import org.springframework.beans.factory.annotation.Autowired;
import ru.tinkoff.ps.ops.test.swt.functions.LimitedIterationsExpandableFunction;

public class Log extends LimitedIterationsExpandableFunction {

    private Ln ln;
    private final int base;

    public Log(final int base) {
        super();
        this.base = base;
        this.ln = new Ln();
    }

    public Log(final int base, Ln ln) {
        super();
        this.base = base;
        this.ln = ln;
    }


    @Override
    public Double calculate(Double x, Double precision) {
        var arg1 = ln.calculate(x, precision);
        var arg2 = ln.calculate((double) base, precision);

        if (arg1 == null || arg2 == null) {
            throw new NullPointerException();
        }

        if (arg2 == 0) {
            throw new ArithmeticException();
        }
        return ln.calculate(x, precision) / ln.calculate((double) base, precision);
    }
}
