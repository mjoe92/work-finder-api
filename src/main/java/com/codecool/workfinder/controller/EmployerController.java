package com.codecool.workfinder.controller;

import com.codecool.workfinder.model.dto.EmployerDto;
import com.codecool.workfinder.model.entity.Employer;
import com.codecool.workfinder.service.EmployerService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/employers")
@Tag(name = "Employer service", description = "Job management for companies")
public class EmployerController extends BaseController<Employer, EmployerDto, String, EmployerService> {

    protected EmployerController(EmployerService service) {
        super(service);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EmployerDto> findById(@PathVariable String id) {
        logger.info("Start 'GET' request: findById(String)");
        EmployerDto employerDto = service.findById(id);
        HttpStatus status = logger.getStatus(employerDto);
        logger.info("Completed 'GET' request: findById(String)");
        return new ResponseEntity<>(employerDto, status);
    }

    @PostMapping
    public ResponseEntity<EmployerDto> registerEmployer(
            @Valid @RequestBody EmployerDto employerDto) {
        logger.info("Start 'POST' request: registerEmployer(EmployerDto)");
        service.save(employerDto);
        HttpStatus status = logger.getStatus(employerDto);
        logger.info("Completed 'POST' request: registerEmployer(EmployerDto)");
        return new ResponseEntity<>(employerDto, status);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<EmployerDto> deleteById(@PathVariable("id") String id){
        logger.info("Start 'DELETE' method: delete(String)");
        EmployerDto dto = service.deleteById(id);
        HttpStatus status = logger.getStatus(dto);
        logger.info("Completed 'DELETE' method: delete(String)");
        return new ResponseEntity<>(dto, status);
    }
}