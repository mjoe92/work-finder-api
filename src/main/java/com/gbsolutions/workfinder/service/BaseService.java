package com.gbsolutions.workfinder.service;

import org.springframework.data.repository.CrudRepository;

import java.util.LinkedList;
import java.util.List;

public abstract class BaseService<E, T> {

    protected final CrudRepository<E, T> repository;

    public BaseService(CrudRepository<E, T> repository) {
        this.repository = repository;
    }

    public List<E> findAll() {
        List<E> list = new LinkedList<>();
        repository.findAll().forEach(list::add);
        return list;
    }
}