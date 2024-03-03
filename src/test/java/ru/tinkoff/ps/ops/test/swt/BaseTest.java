package ru.tinkoff.ps.ops.test.swt;

import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

@SpringJUnitConfig(ConfigurationModule.class)
public class BaseTest {
    protected final static double PRECISION = 0.0000001;
    protected final static double PRECISION_ASSERT = 0.00001;
}