package com.gbsolutions.workfinder.service;

import com.gbsolutions.workfinder.logger.ConsoleLogger;
import com.gbsolutions.workfinder.logger.PhaseLogger;
import org.springframework.data.repository.CrudRepository;

import java.util.LinkedList;
import java.util.List;

public abstract class BaseService<E, T> {

    protected final CrudRepository<E, T> repository;
    protected final ConsoleLogger logger;

    public BaseService(CrudRepository<E, T> repository) {
        this.repository = repository;
        this.logger = new PhaseLogger(this.getClass());
    }

    public List<E> findAllInRepo() {
        List<E> list = new LinkedList<>();
        repository.findAll().forEach(list::add);
        return list;
    }
}