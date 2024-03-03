package ru.tinkoff.ps.ops.test.swt;


import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.extension.Extension;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import ru.tinkoff.ps.ops.test.swt.functions.logariphmic.Ln;
import ru.tinkoff.ps.ops.test.swt.functions.logariphmic.Log;
import ru.tinkoff.ps.ops.test.swt.functions.trigonometric.Cos;
import ru.tinkoff.ps.ops.test.swt.functions.trigonometric.Sin;

@Configuration
@ComponentScan(
        value = "ru.tinkoff.ps.ops.test.swt",
        lazyInit = true
)
public class ConfigurationModule implements Extension {

    @Bean
    public Sin sin() {
        return new Sin();
    }

    @Bean
    public Cos cos() {
        return new Cos();
    }

    @Bean
    public Ln ln() {
        return new Ln();
    }

    @Bean("log10")
    public Log log10() {
        return new Log(10);
    }

    @Bean("log2")
    public Log log2() {
        return new Log(2);
    }
}
