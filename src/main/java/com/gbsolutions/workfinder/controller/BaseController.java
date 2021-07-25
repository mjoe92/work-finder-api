package com.gbsolutions.workfinder.controller;

import com.gbsolutions.workfinder.logger.ConsoleLogger;
import com.gbsolutions.workfinder.logger.PhaseLogger;
import com.gbsolutions.workfinder.service.BaseService;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

public abstract class BaseController<E, D, T, S extends BaseService<E, D, T>> {

    protected final S service;
    protected final ConsoleLogger logger;

    protected BaseController(S service) {
        this.service = service;
        this.logger = new PhaseLogger(this.getClass());
    }
/*

    @GetMapping
    public List<D> findAll(){
        logger.info("Completed 'GET' request: findAll()");
        return service.findAll();
    }

    @GetMapping("/{id}")
    public D findById(@PathVariable("id") T id) {
        logger.info("Completed 'GET' request: findById" +
                "("+ id.getClass().getSimpleName() + ")");
        return service.findById(id);
    }

    @PostMapping
    public void save(@Valid @RequestBody D dto) {
        logger.info("Completed 'POST' request: save" +
                "(" + dto.getClass().getSimpleName() + ")");
        service.save(dto);
    }

    @DeleteMapping("{id}")
    public T deleteById(@PathVariable("id") T id) {
        logger.info("Completed 'DELETE' request: deleteById" +
                "("+ id.getClass().getSimpleName() + ")");
        return service.deleteById(id);
    }
*/

}