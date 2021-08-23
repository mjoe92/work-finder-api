package com.codecool.workfinder.logger;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PhaseLogger implements ConsoleLogger {

    private final Class<?> clazz;
    private final Logger logger;

    public PhaseLogger(Class<?> clazz) {
        this.clazz = clazz;
        this.logger = LoggerFactory.getLogger(clazz);
    }

    @Override
    public void info(String message) {
        logger.info(getDashesForComponent() + message);
    }

    @Override
    public void error(String message) {
        logger.error(getDashesForComponent() + message);
    }

    private String getDashesForComponent() {
        String className = clazz.getSimpleName().toLowerCase();
        return "-".repeat(getNumberOfDashes(className));
    }

    private Integer getNumberOfDashes(String componentName) {
        if (componentName.contains("controller")){
            return 1;
        } else if (componentName.contains("convert")){
            return 2;
        } else if (componentName.contains("service")){
            return 3;
        } else if (componentName.contains("repository")){
            return 4;
        } else if (componentName.contains("mapper")){
            return 4;
        }
        return 0;
    }
}