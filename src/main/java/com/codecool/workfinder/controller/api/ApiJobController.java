package com.codecool.workfinder.controller.api;

import com.codecool.workfinder.handler.HttpResponseHandler;
import com.codecool.workfinder.model.api.adzuna.AdzunaJobCollection;
import com.codecool.workfinder.service.ApiFetchService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Tag(name = "Work API fetcher - Testing environment")
public class ApiJobController {

    @Autowired
    private ApiFetchService service;
    @Autowired
    protected HttpResponseHandler response;

    @Cacheable("adzunaJob")
    @GetMapping("adzuna")
    @Operation(summary = "List all jobs from Adzuna!")
    public ResponseEntity<?> getAllAdzunaJobs() {
        AdzunaJobCollection collection = service.getAdzunaJobCollection("", "", 1L);
        ResponseEntity<?> responseEntity = response.getEntityWithStatus(collection);
        return responseEntity;
    }

    @Cacheable("joobleJob")
    @PostMapping("jooble")
    @Operation(summary = "List all jobs from Jooble")
    public ResponseEntity<?> getAllJoobleJobs() {
        Object collection = service.getJoobleJobCollection("", "");
        ResponseEntity<?> responseEntity = response.getEntityWithStatus(collection);
        return responseEntity;
    }

}