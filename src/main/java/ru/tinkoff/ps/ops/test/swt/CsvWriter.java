package ru.tinkoff.ps.ops.test.swt;


import lombok.SneakyThrows;
import ru.tinkoff.ps.ops.test.swt.functions.SeriesExpandableFunction;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Path;
import java.nio.file.Paths;


public class CsvWriter {

  @SneakyThrows
  public static void write(
      final String filename,
      final SeriesExpandableFunction function,
      final double from,
      final double to,
      final double step,
      final double precision) {
    final Path path = Paths.get(filename);
    final File file = new File(path.toUri());
    if (file.exists()) {
      file.delete();
    }
    if (file.createNewFile()) {
      final PrintWriter printWriter = new PrintWriter(file);
      for (double current = from; current <= to; current += step) {
        printWriter.printf("%.5f, %.5f\n", current, function.calculate(current, precision));
      }
      printWriter.close();
    }
  }
}
