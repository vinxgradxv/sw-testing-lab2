package ru.tinkoff.ps.ops.test.swt.functions;

import java.math.BigDecimal;
import java.util.Objects;

import static java.math.BigDecimal.ONE;
import static java.math.BigDecimal.ZERO;

public abstract class LimitedIterationsExpandableFunction implements SeriesExpandableFunction {

  protected static final int DEFAULT_MAX_ITERATIONS = 2000;

  protected final int maxIterations;

  protected LimitedIterationsExpandableFunction() {
    this.maxIterations = DEFAULT_MAX_ITERATIONS;
  }

  protected void checkValidity(final Double x, final Double precision) {
    Objects.requireNonNull(x, "Function argument can not be null");
    Objects.requireNonNull(precision, "Precision can not be null");
    if (precision <= 0 || precision >= 1) {
      throw new IllegalArgumentException("Precision must be less than one and more than zero");
    }
  }
}
