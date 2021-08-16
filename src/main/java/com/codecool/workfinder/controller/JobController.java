package com.codecool.workfinder.controller;

import com.codecool.workfinder.model.dto.JobDto;
import com.codecool.workfinder.model.entity.Job;
import com.codecool.workfinder.service.JobService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("jobs")
@Tag(name = "Job service", description = "Managing jobs")
public class JobController
        extends BaseController<Job, JobDto, String, JobService> {

    protected JobController(JobService service) {
        super(service);
    }

    @GetMapping("apis/{api_name}")
    @Operation(summary = "List all jobs from Jobs APIs then from local repository with filters!")
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

    @GetMapping("{id}")
    @Operation(summary = "Find job by id!")
    public ResponseEntity<?> findById(@PathVariable String id) {

        logger.info("Start 'GET' request: findById(String)");
        Optional<JobDto> jobDto = service.findById(id);
        ResponseEntity<?> responseEntity = response.getEntityWithStatus(jobDto.orElse(null));
        logger.info("Completed 'GET' request: findById(String)");
        return responseEntity;
    }

    @PostMapping("{employer_id}")
    @Operation(summary = "Register new job into repository or" +
            " edit existed one (ACCESSED ONLY FOR EMPLOYERS)!")
    public ResponseEntity<?> jobRegister(
            @RequestBody @Valid JobDto jobDto,
            @PathVariable("employer_id") String employerId) {

        logger.info("Start 'POST' request: jobRegister(Job, String)");
        jobDto = returnWithApiCheck(service
                .assignJobToEmployerThenSave(jobDto, employerId), employerId);
        ResponseEntity<?> responseEntity = response.getEntityWithStatus(jobDto);
        logger.info("Completed 'POST' request: jobRegister(Job, String)");
        return responseEntity;
    }

    @DeleteMapping("{job_id}/{employer_id}")
    @Operation(summary = "Delete job by id in repository!")
    public ResponseEntity<?> deleteById(
            @PathVariable("job_id") String jobId,
            @PathVariable("employer_id") String employerId) {

        logger.info("Start 'DELETE' request: jobRegister(String, Long)");
        Optional<JobDto> jobDto = returnWithApiCheck(service.deleteById(jobId), employerId);
        ResponseEntity<?> responseEntity = response.getEntityWithStatus(jobDto.orElse(null));
        logger.info("Completed 'DELETE' request: jobRegister(String, Long)");
        return responseEntity;
    }

    private <R> R returnWithApiCheck(R response, String employerId) {
        if (service.isValidEmployer(employerId)) {
            return response;
        }
        logger.error("Invalid employer name!");
        return null;
    }
}