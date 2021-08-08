package com.codecool.workfinder.controller;

import com.aventrix.jnanoid.jnanoid.NanoIdUtils;
import com.codecool.workfinder.model.dto.EmployerDto;
import com.codecool.workfinder.model.entity.Employer;
import com.codecool.workfinder.service.EmployerService;
import io.swagger.v3.oas.annotations.tags.Tag;
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
    public EmployerDto findById(@PathVariable String id) {
        logger.info("Start 'GET' request: findById(String)");
        EmployerDto employerDto = service.findById(id);
        logger.info("Completed 'GET' request: findById(String)");
        return employerDto;
    }

    @PostMapping
    public String saveAndReturnId(@Valid @RequestBody Employer employer) {
        logger.info("Start 'POST' request: saveAndReturnId(Employer)");
        employer.setId(NanoIdUtils.randomNanoId());
        service.saveAndReturnId(employer);
        logger.info("Completed 'POST' request: saveAndReturnId(Employer)");
        return employer.getId();
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable String id) {
        logger.info("Start 'DELETE' request: deleteById(String)");
        service.deleteById(id);
        logger.info("Completed 'DELETE' request: deleteById(String)");
    }
}