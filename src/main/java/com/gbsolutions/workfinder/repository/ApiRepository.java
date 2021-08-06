package com.gbsolutions.workfinder.repository;

import com.gbsolutions.workfinder.logger.ConsoleLogger;
import com.gbsolutions.workfinder.logger.PhaseLogger;
import com.gbsolutions.workfinder.model.entity.Api;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ApiRepository extends JpaRepository<Api, String> {
    default void logMessage(String message) {
        ConsoleLogger logger = new PhaseLogger(this.getClass());
        logger.info(message);
    }
}