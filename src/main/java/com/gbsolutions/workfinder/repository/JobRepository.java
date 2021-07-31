package com.gbsolutions.workfinder.repository;

import com.gbsolutions.workfinder.logger.ConsoleLogger;
import com.gbsolutions.workfinder.logger.PhaseLogger;
import com.gbsolutions.workfinder.model.entity.Job;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.validation.constraints.Size;
import java.util.List;

@Repository
public interface JobRepository extends JpaRepository<Job, Long> {
    List<Job> findAll();
    List<Job> findAllByTitleOrLocation(
            @Size(max = 50, message = "Field 'title' size is max. 50!") String title,
            @Size(max = 50, message = "Field 'location' size is max. 50!") String location
    );

    default void logMessage(String message) {
        ConsoleLogger logger = new PhaseLogger(this.getClass());
        logger.info(message);
    }
}