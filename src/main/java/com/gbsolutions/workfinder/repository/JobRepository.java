package com.gbsolutions.workfinder.repository;

import com.gbsolutions.workfinder.model.entity.Job;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JobRepository extends CrudRepository<Job, String> {
    List<Job> findAll();
}
