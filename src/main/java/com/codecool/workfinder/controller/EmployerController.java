package com.codecool.workfinder.controller;

import com.codecool.workfinder.model.dto.EmployerDto;
import com.codecool.workfinder.model.dto.JobDto;
import com.codecool.workfinder.model.entity.Employer;
import com.codecool.workfinder.service.EmployerService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import java.util.Optional;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@RestController
@RequestMapping("employers")
@Tag(name = "Employer service", description = "Job management for companies")
public class EmployerController extends BaseController<Employer, EmployerDto, String, EmployerService> {

    protected EmployerController(EmployerService service) {
        super(service);
    }

    @GetMapping("{id}")
    @Operation(summary = "Find employer by id!")
    public ResponseEntity<?> findById(@PathVariable String id) {
        logger.info("Start 'GET' request: findById(String)");
        Optional<EmployerDto> employerDto = service.findById(id);
        ResponseEntity<?> responseEntity = response.getEntityWithStatus(employerDto.orElse(null));
        logger.info("Completed 'GET' request: findById(String)");
        return responseEntity;
    }

    @PostMapping
    @Operation(summary = "Register new employer into repository!")
    public ResponseEntity<?> registerEmployer(
            @Valid @RequestBody EmployerDto employerDto) {
        logger.info("Start 'POST' request: registerEmployer(EmployerDto)");
        service.save(employerDto);
        ResponseEntity<?> responseEntity = response.getEntityWithStatus(employerDto);
        logger.info("Completed 'POST' request: registerEmployer(EmployerDto)");
        return responseEntity;
    }

    @PutMapping("{employer_id}/job")
    @Operation(summary = "Create a job for employer!")
    public ResponseEntity<?> createJobToEmployer(
            @Valid @RequestBody JobDto jobDto,
            @PathVariable("employer_id") String employerId) {

        logger.info("Start 'PUT' request: createJobForEmployer(EmployerDto)");
        boolean isRegistered = service.isEmployerRegistered(employerId);
        if (!isRegistered) {
            return new ResponseEntity<>("No matching employer id!", NOT_FOUND);
        }
        service.createJobToEmployer(jobDto, employerId);
        ResponseEntity<?> responseEntity = response.getEntityWithStatus(jobDto);
        logger.info("Completed 'PUT' request: createJobForEmployer(EmployerDto)");
        return responseEntity;
    }

    @DeleteMapping("{id}")
    @Operation(summary = "Delete job by id!")
    public ResponseEntity<?> deleteById(@PathVariable("id") String id){
        logger.info("Start 'DELETE' method: delete(String)");
        Optional<EmployerDto> employerDto = service.deleteById(id);
        ResponseEntity<?> responseEntity = response.getEntityWithStatus(employerDto.orElse(null));
        logger.info("Completed 'DELETE' method: delete(String)");
        return responseEntity;
    }
}