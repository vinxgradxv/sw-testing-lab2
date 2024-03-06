package ru.tinkoff.ps.ops.test.swt.functions.trigonometric;

import org.springframework.beans.factory.annotation.Autowired;
import ru.tinkoff.ps.ops.test.swt.functions.LimitedIterationsExpandableFunction;

public class Cos extends LimitedIterationsExpandableFunction {

    private final Sin sin;

    public Cos() {
        super();
        this.sin = new Sin();
    }

    public Cos(Sin sin) {
        super();
        this.sin = sin;
    }

    @Override
    public Double calculate(Double x, Double precision) {
        return sin.calculate(x + Math.PI / 2d, precision);
    }
}
