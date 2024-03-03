package ru.tinkoff.ps.ops.test.swt;

import ru.tinkoff.ps.ops.test.swt.functions.FunctionsSystem;
import ru.tinkoff.ps.ops.test.swt.functions.logariphmic.Ln;
import ru.tinkoff.ps.ops.test.swt.functions.logariphmic.Log;
import ru.tinkoff.ps.ops.test.swt.functions.trigonometric.Cos;
import ru.tinkoff.ps.ops.test.swt.functions.trigonometric.Sin;


public class Main {

    private static final double PRECISION = 0.000001;

    public static void main(String[] args) {
        final Cos cos = new Cos();
        CsvWriter.write("csv/cos.csv", cos, -1, 2 * Math.PI, 0.1, PRECISION);

        final Sin sin = new Sin();
        CsvWriter.write("csv/sin.csv", sin, -1, 2 * Math.PI, 0.1, PRECISION);

        final Ln ln = new Ln();
        CsvWriter.write("csv/ln.csv", ln, 0.1, 20, 0.1, PRECISION);

        final Log log2 = new Log(2);
        CsvWriter.write("csv/log2.csv", log2, 0.1, 20, 0.1, PRECISION);

        final Log log10 = new Log(10);
        CsvWriter.write("csv/log10.csv", log10, 0.1, 20, 0.1, PRECISION);

        final FunctionsSystem func = new FunctionsSystem();
        CsvWriter.write("csv/func.csv", func, -2, 2, 0.1, PRECISION);
    }
}