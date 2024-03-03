package ru.tinkoff.ps.ops.test.swt.functions;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import ru.tinkoff.ps.ops.test.swt.functions.logariphmic.Ln;
import ru.tinkoff.ps.ops.test.swt.functions.logariphmic.Log;
import ru.tinkoff.ps.ops.test.swt.functions.trigonometric.Cos;

public class FunctionsSystem implements SeriesExpandableFunction{
    @Autowired
    private Cos cos;
    @Autowired
    private Ln ln;
    @Autowired
    @Qualifier("log10")
    private Log log10;
    @Autowired
    @Qualifier("log2")
    private Log log2;

    public FunctionsSystem() {
        this.cos = new Cos();
        this.ln = new Ln();
        this.log10 = new Log(10);
        this.log2 = new Log(2);
    }


    @Override
    public Double calculate(Double x, Double precision) {
        if (x <= 0) {
            return cos.calculate(x, precision);
        }
        else {
            return Math.pow(ln.calculate(x, precision) * log2.calculate(x, 2d) / Math.pow(log10.calculate(x, precision), 3) * log2.calculate(x, precision), 2) + ln.calculate(x, precision);
        }
    }
}
