package com.codecool.workfinder.controller;

import com.codecool.workfinder.model.dto.ClientDto;
import com.codecool.workfinder.model.dto.JobDto;
import com.codecool.workfinder.model.entity.Client;
import com.codecool.workfinder.service.ClientService;
import com.codecool.workfinder.service.JobService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.UUID;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
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
    public ResponseEntity<ClientDto> findById(@PathVariable String id) {
        logger.info("Start 'GET' request: findById(String)");
        ClientDto clientDto = service.findById(UUID.fromString(id));
        HttpStatus status = clientDto != null ? OK : BAD_REQUEST;
        logger.info("Completed 'GET' request: findById(String)");
        return new ResponseEntity<>(clientDto, status);
    }

    @PostMapping
    public ResponseEntity<ClientDto> registerClient(
            @Valid @RequestBody ClientDto clientDto) {
        logger.info("Start 'POST' request: registerClient(ClientDto)");
        HttpStatus status = logger.getStatus(clientDto);
        service.save(clientDto);
        logger.info("Completed 'POST' request: save(ClientDto)");
        return new ResponseEntity<>(clientDto, status);
    }

    @PutMapping("/{client_id}/job/{job_id}")
    public ResponseEntity<String> registerJobForClient(
            @PathVariable("client_id") String clientId,
            @PathVariable("job_id") String jobId) {

        logger.info("Start 'PUT' request: registerJobForClient(String, String)");
        ResponseEntity<ClientDto> clientEntity = findById(clientId);
        HttpStatus status = clientEntity.getStatusCode();
        if (status == BAD_REQUEST) {
            return new ResponseEntity<>("No matching client id!", BAD_REQUEST);
        }
        ClientDto clientDto = clientEntity.getBody();

        JobDto jobDto = jobService.findById(UUID.fromString(jobId));
        if (jobDto == null) {
            return new ResponseEntity<>("No matching job id!", BAD_REQUEST);
        }

        service.registerJobForClient(clientDto, jobDto);

        logger.info("Completed 'PUT' request: registerJobForClient(String, String)");
        return new ResponseEntity<>("", HttpStatus.OK);
    }
}