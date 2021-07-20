package com.gbsolutions.workfinder.logger;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PhaseLogger implements ConsoleLogger {

    private final Logger logger;

    public PhaseLogger(Class<?> clazz) {
        this.logger = LoggerFactory.getLogger(clazz);
    }

    @Override
    public void error(String message) {
        logger.error(message);
    }
}