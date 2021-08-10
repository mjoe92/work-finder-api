package com.codecool.workfinder.service;

import com.codecool.workfinder.logger.ConsoleLogger;
import com.codecool.workfinder.logger.PhaseLogger;
import com.codecool.workfinder.model.mapper.GenericMapper;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.LinkedList;
import java.util.List;

public abstract class BaseService<E, D, T> {

    protected JpaRepository<E, T> repository;
    protected GenericMapper<E, D> mapper;
    protected ConsoleLogger logger;

    public BaseService(JpaRepository<E, T> repository,
                       GenericMapper<E, D> mapper) {
        this.repository = repository;
        this.mapper = mapper;
        this.logger = new PhaseLogger(this.getClass());
    }

    public List<D> findAllInRepo() {
        String repoName = repository.getClass().getSimpleName();
        List<E> entityList = repository.findAll();
        logger.info("Completed accessing repository '" + repoName + "'");
        List<D> dtoList = mapper.toDtoList(entityList);
        mapper.logInfo("Completed converting to dtoList!");
        logger.info("Completed coupling to controller: findAllInRepo()");
        return dtoList;
    }

    public D findById(T id) {
        String repoName = repository.getClass().getSimpleName();
        E entity = repository.getById(id);
        logger.info("Completed accessing repository '" + repoName + "'");
        D dto = mapper.toDto(entity);
        String dtoName = dto.getClass().getSimpleName();
        mapper.logInfo("Completed converting to " + dtoName + "!");
        logger.info("Completed coupling to controller: findById()");
        return dto;
    }
}