package com.codecool.workfinder.controller;

import com.codecool.workfinder.service.JobService;
import com.codecool.workfinder.model.dto.JobDto;
import com.codecool.workfinder.model.entity.Job;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URL;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("jobs")
@Tag(name = "Job service", description = "Managing jobs")
public class JobController
        extends BaseController<Job, JobDto, UUID, JobService> {

    protected JobController(JobService service) {
        super(service);
    }

    @GetMapping("{api_key}")
    @Operation(summary = "List all jobs from repository and Jobs APIs!")
    public List<JobDto> listJobsBy(
            @PathVariable("api_key") String apiKey,
            @RequestParam(required = false) String title,
            @RequestParam(required = false) String location
            ) {
        logger.info("Start 'GET' request: listJobsBy(String, String...)");
        List<JobDto> list = returnWithKeyCheck(service
                .listJobsBy(apiKey, title, location), apiKey);
        logger.info("Completed 'GET' request: listJobsBy(String, String...)");
        return list;
    }

    @PostMapping("{api_key}")
    @Operation(summary = "Register job into repository!")
    public URL jobRegister(@Valid @RequestBody Job job,
                           @PathVariable("api_key") String apiKey) {
        logger.info("Start 'POST' request: jobRegister(Job, String)");
        URL url = returnWithKeyCheck(service
                .saveAndReturnUrl(job).orElse(null), apiKey);
        logger.info("Completed 'POST' request: jobRegister(Job, String)");
        return url;
    }

    @DeleteMapping("{api_key}/{id}")
    @Operation(summary = "Delete job by id in repository!")
    public JobDto deleteById(@PathVariable("api_key") String apiKey,
                           @PathVariable("id") UUID id) {
        logger.info("Start 'DELETE' request: jobRegister(String, Long)");
        JobDto jobDto = returnWithKeyCheck(service.deleteById(id), apiKey);
        logger.info("Completed 'DELETE' request: jobRegister(String, Long)");
        return jobDto;
    }

    private <R> R returnWithKeyCheck(R response, String apiKey) {
        if (service.isValidApiKey(apiKey)) {
            return response;
        }
        logger.error("Invalid API key!");
        return null;
    }
}