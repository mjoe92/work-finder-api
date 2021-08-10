package com.codecool.workfinder.model.mapper;

import com.codecool.workfinder.logger.ConsoleLogger;
import com.codecool.workfinder.logger.PhaseLogger;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;

@Component
public interface GenericMapper<E, D> {

    E toEntity(D dto);
    List<E> toEntityList(List<D> dtoList);
    Set<E> toEntitySet(Set<D> dtoSet);
    D toDto(E entity);
    List<D> toDtoList(List<E> entityList);
    Set<D> toDtoSet(Set<D> entitySet);

    default void logInfo(String message) {
        ConsoleLogger logger = new PhaseLogger(this.getClass());
        System.out.println(this.getClass().getSimpleName());
        logger.info(message);
    }
}