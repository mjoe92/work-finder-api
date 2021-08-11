package com.codecool.workfinder.controller.api;

import com.codecool.workfinder.model.api.adzuna.AdzunaJobCollection;
import com.codecool.workfinder.service.ApiFetchService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Tag(name = "Work API fetcher")
public class ApiFetchController {

    @Autowired
    private ApiFetchService service;

    @Cacheable("apiJob")
    @GetMapping("{api_name}")
    @Operation(summary = "List all jobs from Jobs APIs!")
    public AdzunaJobCollection getAllApiJobs(
            @PathVariable("api_name") String apiName) {
        return service.getJobCollection(apiName, "", "", 1L);
    }
}