package com.codecool.workfinder.handler;

import com.codecool.workfinder.logger.ConsoleLogger;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;

@Component
public class BindingErrorHandler {

    public void bindingResult(BindingResult bindingResult, ConsoleLogger logger){
        if (bindingResult.hasErrors()) {
            ObjectError objectError = bindingResult.getAllErrors().get(0);
            String message = "Object name:" + objectError.getObjectName() + ", error code:" + objectError.getCode() + ", error message:" + objectError.getDefaultMessage();
            logger.error(message);
            throw new RuntimeException(message);
        }
    }
}
