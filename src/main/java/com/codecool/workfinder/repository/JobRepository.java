package com.codecool.workfinder.repository;

import com.codecool.workfinder.logger.ConsoleLogger;
import com.codecool.workfinder.logger.PhaseLogger;
import com.codecool.workfinder.model.entity.Job;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JobRepository extends JpaRepository<Job, String> {

    default void logInfo(String message) {
        ConsoleLogger logger = new PhaseLogger(this.getClass());
        logger.info(message);
    }
}