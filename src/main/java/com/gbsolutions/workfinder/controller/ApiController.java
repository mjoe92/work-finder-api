package com.gbsolutions.workfinder.controller;

import com.gbsolutions.workfinder.model.api.adzuna.ApiJobCollection;
import com.gbsolutions.workfinder.model.entity.Job;
import com.gbsolutions.workfinder.service.FetchApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ApiController {

    @Autowired
    private FetchApiService service;

    private final static String API_JOB = "api_job";
    private final static String JOB_LIST = "job_list";

    @Cacheable(API_JOB)
    @GetMapping("/" + API_JOB)
    public ApiJobCollection getAllApiJobs() {
        return service.getJobCollection();
    }

    @Cacheable(JOB_LIST)
    @GetMapping("/" + JOB_LIST)
    public List<Job> getAllJobs() {
        return service.getJobListBy("", "");
    }
}