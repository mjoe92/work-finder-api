package com.codecool.workfinder.repository;

import com.codecool.workfinder.logger.ConsoleLogger;
import com.codecool.workfinder.logger.PhaseLogger;
import com.codecool.workfinder.model.entity.Employer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployerRepository extends JpaRepository<Employer, String> {
    default void logInfo(String message) {
        ConsoleLogger logger = new PhaseLogger(this.getClass());
        logger.info(message);
    }
}