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

    @Override
    public Double calculate(double x, double precision) {
        return ln.calculate(x, precision) / ln.calculate(base, precision);
    }
}
