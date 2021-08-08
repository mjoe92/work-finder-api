package com.codecool.workfinder.service;

import com.codecool.workfinder.logger.ConsoleLogger;
import com.codecool.workfinder.logger.PhaseLogger;
import com.codecool.workfinder.model.mapper.GenericMapper;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.LinkedList;
import java.util.List;

public abstract class BaseService<E, D, T> {

    protected final JpaRepository<E, T> repository;
    protected final GenericMapper<E, D> mapper;
    protected final ConsoleLogger logger;

    public BaseService(JpaRepository<E, T> repository,
                       GenericMapper<E, D> mapper) {
        this.repository = repository;
        this.mapper = mapper;
        this.logger = new PhaseLogger(this.getClass());
    }

    public List<D> findAllInRepo() {
        List<D> list = new LinkedList<>();
        logger.info("Completed accessing repository '"
                + repository.getClass().getSimpleName() + "'");
        repository.findAll().forEach(e -> list.add(mapper.toDto(e)));
        logger.info("Completed coupling to controller: findAllInRepo()");
        return list;
    }

    public D findById(T id) {
        //repository.logMessage("Completed accessing repository!");
        D dto = mapper.toDto(repository.findById(id).orElse(null));
        logger.info("Completed coupling to controller: findById()");
        return dto;
    }

    public void save(D dto) {
        logger.info("Completed accessing repository '"
                + repository.getClass().getSimpleName() + "'");
        repository.save(mapper.toEntity(dto));
        logger.info("Completed coupling to controller: save()");
    }

    public T deleteById(T id) {
        logger.info("Completed accessing repository '"
                + repository.getClass().getSimpleName() + "'");
        repository.deleteById(id);
        logger.info("Completed coupling to controller: deleteById()");
        return id;
    }
}