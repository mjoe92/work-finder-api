package com.codecool.workfinder.logger;

import org.springframework.http.HttpStatus;

public interface ConsoleLogger {

    void info(String message);
    void error(String message);
    <M> HttpStatus getStatus(M model);
}