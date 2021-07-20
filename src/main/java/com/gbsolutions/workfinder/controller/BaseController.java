package com.gbsolutions.workfinder.controller;

import com.gbsolutions.workfinder.service.BaseService;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

public abstract class BaseController<E, T, S extends BaseService<E, T>> {

    protected final S service;

    protected BaseController(S service) {
        this.service = service;
    }

    @GetMapping
    public List<E> findAll(){
        return service.findAll();
    }
}