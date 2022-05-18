package com.accenture.gradetool.core.error;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ConsoleExceptionLogger implements ExceptionLogger {

    private final Logger logger;

    @Autowired
    public ConsoleExceptionLogger(Logger logger) {
        this.logger = logger;
    }

    @Override
    public String logException(Exception e) {
        logger.error(e.getMessage(), e);

        return null;
    }

}
