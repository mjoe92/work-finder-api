package com.gbsolutions.workfinder.repository;

import com.gbsolutions.workfinder.model.entity.Job;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.validation.constraints.Size;
import java.util.List;

@Repository
public interface JobRepository extends CrudRepository<Job, Long> {
    List<Job> findAll();
    List<Job> findAllByTitleOrLocation(
            @Size(max = 50, message = "Field 'title' size is max. 50!") String title,
            @Size(max = 50, message = "Field 'location' size is max. 50!") String location
    );
}