package com.codecool.workfinder.controller;

import com.codecool.workfinder.service.JobService;
import com.codecool.workfinder.model.dto.JobDto;
import com.codecool.workfinder.model.entity.Job;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
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

    @GetMapping("{api_name}")
    @Operation(summary = "List all jobs from repository and Jobs APIs!")
    public ResponseEntity<?> listJobsBy(
            @PathVariable("api_name") String apiName,
            @RequestParam Long pages,
            @RequestParam(required = false) String title,
            @RequestParam(required = false) String location) {

        logger.info("Start 'GET' request: listJobsBy(String, String...)");
        List<JobDto> list = service.listJobsBy(apiName, pages, title, location);
        ResponseEntity<?> responseEntity = response.getEntityWithStatus(list);
        logger.info("Completed 'GET' request: listJobsBy(String, String...)");
        return responseEntity;
    }

    @PostMapping("{employer_id}")
    @Operation(summary = "Register new job into repository!")
    public JobDto jobRegister(
            @RequestBody @Valid JobDto jobDto,
            @PathVariable("employer_id") String employerId) {

        logger.info("Start 'POST' request: jobRegister(Job, String)");
        jobDto = returnWithApiCheck(service.save(jobDto), employerId);
        logger.info("Completed 'POST' request: jobRegister(Job, String)");
        return jobDto;
    }

    @DeleteMapping("{api_key}/{id}")
    @Operation(summary = "Delete job by id in repository!")
    public JobDto deleteById(
            @PathVariable("api_key") String apiKey,
            @PathVariable("id") UUID id) {

        logger.info("Start 'DELETE' request: jobRegister(String, Long)");
        JobDto jobDto = returnWithApiCheck(service.deleteById(id), apiKey);
        logger.info("Completed 'DELETE' request: jobRegister(String, Long)");
        return jobDto;
    }

    private <R> R returnWithApiCheck(R response, String employerId) {
        if (service.isValidEmployer(employerId)) {
            return response;
        }
        logger.error("Invalid API name!");
        return null;
    }
}