package com.gbsolutions.workfinder.controller;

import com.gbsolutions.workfinder.logger.ConsoleLogger;
import com.gbsolutions.workfinder.logger.PhaseLogger;
import com.gbsolutions.workfinder.service.BaseService;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

public abstract class BaseController<E, D, T, S extends BaseService<E, D, T>> {

    protected final S service;
    protected final ConsoleLogger logger;

    protected BaseController(S service) {
        this.service = service;
        this.logger = new PhaseLogger(this.getClass());
    }

    @GetMapping
    public List<D> findAllInRepo(){
        logger.info("Start 'GET' request: findAll()");
        List<D> list = service.findAllInRepo();
        logger.info("Completed 'GET' request: findAll()");
        return list;
    }
}