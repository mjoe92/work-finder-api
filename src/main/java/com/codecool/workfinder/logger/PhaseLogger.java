package com.codecool.workfinder.logger;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.OK;

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

    public <M> HttpStatus getStatus(M model) {
        return model != null ? OK : BAD_REQUEST;
    }
}