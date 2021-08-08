package com.codecool.workfinder.controller;

import com.codecool.workfinder.logger.ConsoleLogger;
import com.codecool.workfinder.logger.PhaseLogger;
import com.codecool.workfinder.service.BaseService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

public abstract class BaseController<E, D, T, S extends BaseService<E, D, T>> {

    protected final S service;
    protected final ConsoleLogger logger;

    protected BaseController(S service) {
        this.service = service;
        this.logger = new PhaseLogger(this.getClass());
    }

    @Value("${server.port}")
    private int port;

    @GetMapping
    public List<D> findAllJobsInRepo(){
        logger.info("Running port: " + port);
        logger.info("Start 'GET' request: findAll()");
        List<D> list = service.findAllInRepo();
        logger.info("Completed 'GET' request: findAll()");
        return list;
    }
}