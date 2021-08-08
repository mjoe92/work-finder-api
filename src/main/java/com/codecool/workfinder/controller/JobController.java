package com.codecool.workfinder.controller;

import com.codecool.workfinder.service.JobService;
import com.codecool.workfinder.model.dto.JobDto;
import com.codecool.workfinder.model.entity.Job;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URL;
import java.util.List;

@RestController
@RequestMapping("/positions")
@Tag(name = "Job service", description = "Posting jobs")
public class JobController
        extends BaseController<Job, JobDto, Long, JobService> {

    protected JobController(JobService service) {
        super(service);
    }

    @GetMapping("/{api_key}")
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

    @PostMapping("/{api_key}")
    public URL jobRegister(@Valid @RequestBody Job job,
                           @PathVariable("api_key") String apiKey) {
        logger.info("Start 'POST' request: jobRegister(Job, String)");
        URL url = returnWithKeyCheck(service
                .saveAndReturnUrl(job).orElse(null), apiKey);
        logger.info("Completed 'POST' request: jobRegister(Job, String)");
        return url;
    }

    @DeleteMapping("/{api_key}/{id}")
    public Long deleteById(@PathVariable("api_key") String apiKey,
                           @PathVariable("id") Long id) {
        logger.info("Start 'DELETE' request: jobRegister(String, Long)");
        id = returnWithKeyCheck(service.deleteById(id), apiKey);
        logger.info("Completed 'DELETE' request: jobRegister(String, Long)");
        return id;
    }

    private <R> R returnWithKeyCheck(R response, String apiKey) {
        if (service.isValidApiKey(apiKey)) {
            return response;
        }
        logger.error("Invalid API key!");
        return null;
    }
}