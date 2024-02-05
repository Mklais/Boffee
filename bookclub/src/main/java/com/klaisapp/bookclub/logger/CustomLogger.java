package com.klaisapp.bookclub.logger;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CustomLogger {

    private final Logger logger;

    public CustomLogger(Class<?> clazz) {
        this.logger = LoggerFactory.getLogger(clazz);
    }

    public void info(LogCategory category, String operation, String details) {
        if (logger.isInfoEnabled()) {
            logger.info(LogMessageFormatter.format(category, null, operation, details));
        }
    }

    // Overloaded method for logging with subsection
    public void info(LogCategory category, LogSubsection subsection, String operation, String details) {
        if (logger.isInfoEnabled()) {
            logger.info(LogMessageFormatter.format(category, subsection, operation, details));
        }
    }
}
