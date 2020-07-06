package org.ko.shin.core;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LoggerTests {

    private static Logger logger = LoggerFactory.getLogger(LoggerTests.class);

    @Test
    public void whenLoggerError() {
        try {
            int i = 2 / 0;
        } catch (Exception e) {
            logger.error("{}", e.getMessage());
        }
    }
}
