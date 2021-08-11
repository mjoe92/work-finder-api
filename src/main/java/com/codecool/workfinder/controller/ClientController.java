package com.codecool.workfinder.controller;

import com.codecool.workfinder.model.dto.ClientDto;
import com.codecool.workfinder.model.dto.JobDto;
import com.codecool.workfinder.model.entity.Client;
import com.codecool.workfinder.service.ClientService;
import com.codecool.workfinder.service.JobService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.UUID;

import static org.springframework.http.HttpStatus.NOT_FOUND;
import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("/clients")
@Tag(name = "Client service", description = "Signing applicants, employees")
public class ClientController extends BaseController<Client, ClientDto, UUID, ClientService> {

    private final JobService jobService;

    protected ClientController(ClientService clientService, JobService jobService) {
        super(clientService);
        this.jobService = jobService;
    }

    @GetMapping("/{id}")
    @Operation(summary = "Find client by id!")
    public ResponseEntity<?> findById(@PathVariable String id) {
        logger.info("Start 'GET' request: findById(String)");
        ClientDto clientDto = service.findById(UUID.fromString(id)).orElse(null);
        ResponseEntity<?> responseEntity = response.getEntityWithStatus(clientDto);
        logger.info("Completed 'GET' request: findById(String)");
        return responseEntity;
    }

    @PostMapping
    @Operation(summary = "Register new client into repository!")
    public ResponseEntity<?> registerClient(
            @Valid @RequestBody ClientDto clientDto) {
        logger.info("Start 'POST' request: registerClient(ClientDto)");
        ResponseEntity<?> responseEntity = response.getEntityWithStatus(clientDto);
        service.save(clientDto);
        logger.info("Completed 'POST' request: save(ClientDto)");
        return responseEntity;
    }

    @PutMapping("{client_id}/job/{job_id}")
    @Operation(summary = "Assign a job for client!")
    public ResponseEntity<String> applyJobForClient(
            @PathVariable("client_id") String clientId,
            @PathVariable("job_id") String jobId) {

        logger.info("Start 'PUT' request: registerJobForClient(String, String)");
        ResponseEntity<?> clientEntity = findById(clientId);
        HttpStatus status = clientEntity.getStatusCode();
        if (status == NOT_FOUND) {
            return new ResponseEntity<>("No matching client id!", NOT_FOUND);
        }
        ClientDto clientDto = (ClientDto) clientEntity.getBody();

        JobDto jobDto = jobService.findById(UUID.fromString(jobId)).orElse(null);
        if (jobDto == null) {
            return new ResponseEntity<>("No matching job id!", NOT_FOUND);
        }

        service.registerJobForClient(clientDto, jobDto);

        logger.info("Completed 'PUT' request: registerJobForClient(String, String)");
        return new ResponseEntity<>("", HttpStatus.OK);
    }
}