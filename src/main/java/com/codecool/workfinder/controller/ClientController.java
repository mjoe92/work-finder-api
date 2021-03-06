package com.codecool.workfinder.controller;

import com.codecool.workfinder.model.dto.ClientDto;
import com.codecool.workfinder.model.dto.JobDto;
import com.codecool.workfinder.model.entity.Client;
import com.codecool.workfinder.service.ClientService;
import com.codecool.workfinder.service.JobService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@RestController
@RequestMapping("/clients")
@Tag(name = "Client service", description = "Signing applicants, employees")
public class ClientController
        extends BaseController<Client, ClientDto, String, ClientService> {

    private final JobService jobService;

    protected ClientController(ClientService clientService, JobService jobService) {
        super(clientService);
        this.jobService = jobService;
    }

    @GetMapping("/{id}")
    @Operation(summary = "Find client by id!")
    public ResponseEntity<?> findById(@PathVariable String id) {

        logger.info("Start 'GET' request: findById(String)");
        ClientDto clientDto = service.findById(id).orElse(null);
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
    @Operation(summary = "Apply job for client!")
    public ResponseEntity<?> applyJobForClient(
            @PathVariable("client_id") String clientId,
            @PathVariable("job_id") String jobId) {

        logger.info("Start 'PUT' request: registerJobForClient(String, String)");

        ResponseEntity<?> clientEntity = response
                .getEntityWithStatus(service.findById(clientId).orElse(null));
        if (clientEntity.getStatusCode() == NOT_FOUND) {
            //clientEntity.getHeaders().add("problem", "Client doesn't exist!");
            return clientEntity;
        }

        ResponseEntity<?> jobEntity = response
                .getEntityWithStatus(jobService.findById(jobId).orElse(null));
        if (jobEntity.getStatusCode() == NOT_FOUND) {
            //jobEntity.getHeaders().add("problem", "Job doesn't exist!");
            return jobEntity;
        }

        service.addJobForClient((ClientDto) clientEntity.getBody(), (JobDto) jobEntity.getBody());
        logger.info("Completed 'PUT' request: registerJobForClient(String, String)");
        return clientEntity;
    }

    @DeleteMapping("{id}")
    @Operation(summary = "Delete client by id!")
    public ResponseEntity<?> deleteById(@PathVariable("id") String id){

        logger.info("Start 'DELETE' method: delete(String)");
        Optional<ClientDto> clientDto = service.deleteById(id);
        ResponseEntity<?> responseEntity = response.getEntityWithStatus(clientDto.orElse(null));
        logger.info("Completed 'DELETE' method: delete(String)");
        return responseEntity;
    }
}