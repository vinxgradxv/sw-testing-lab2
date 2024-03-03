package ru.tinkoff.ps.ops.test.swt.functions.trigonometric;

import org.springframework.beans.factory.annotation.Autowired;
import ru.tinkoff.ps.ops.test.swt.functions.LimitedIterationsExpandableFunction;

public class Cos extends LimitedIterationsExpandableFunction {

    private final Sin sin;

    public Cos() {
        super();
        this.sin = new Sin();
    }

    @Override
    public Double calculate(double x, double precision) {
        return sin.calculate(x + Math.PI / 2d, precision);
    }
}