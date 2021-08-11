package com.codecool.workfinder.controller;

import com.codecool.workfinder.handler.BindingErrorHandler;
import com.codecool.workfinder.handler.HttpResponseHandler;
import com.codecool.workfinder.logger.ConsoleLogger;
import com.codecool.workfinder.logger.PhaseLogger;
import com.codecool.workfinder.service.BaseService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

public abstract class BaseController<E, D, T, S extends BaseService<E, D, T>> {

    protected final S service;
    protected final ConsoleLogger logger;
    @Autowired
    protected HttpResponseHandler response;
    @Autowired
    protected BindingErrorHandler errorHandler;

    protected BaseController(S service) {
        this.service = service;
        this.logger = new PhaseLogger(this.getClass());
    }

    @GetMapping
    @Operation(summary = "List all in repository!")
    public ResponseEntity<?> findAllInRepo(){
        logger.info("Start 'GET' request: findAll()");
        List<D> list = service.findAllInRepo();
        ResponseEntity<?> responseEntity = response.getEntityWithStatus(list);
        logger.info("Completed 'GET' request: findAll()");
        return responseEntity;
    }
}