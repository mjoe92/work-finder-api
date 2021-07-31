package com.gbsolutions.workfinder.controller;

import com.gbsolutions.workfinder.model.dto.ClientDto;
import com.gbsolutions.workfinder.model.dto.EmployerDto;
import com.gbsolutions.workfinder.model.entity.Client;
import com.gbsolutions.workfinder.model.entity.Employer;
import com.gbsolutions.workfinder.service.ClientService;
import com.gbsolutions.workfinder.service.EmployerService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.UUID;

@RestController
@RequestMapping("/clients")
@Tag(name = "Client service", description = "Signing applicants, employees")
public class ClientController extends BaseController<Client, ClientDto, UUID, ClientService> {

    protected ClientController(ClientService clientService) {
        super(clientService);
    }

    @GetMapping("/{id}")
    public ClientDto findById(@PathVariable String id) {
        logger.info("Start 'GET' request: findById(String)");
        ClientDto clientDto = service.findById(UUID.fromString(id));
        logger.info("Completed 'GET' request: findById(String)");
        return clientDto;
    }

    @PostMapping
    public UUID saveAndReturnId(@Valid @RequestBody ClientDto clientDto) {
        logger.info("Start 'POST' request: save(ClientDto)");
        UUID id = service.saveAndReturnId(clientDto);
        logger.info("Completed 'POST' request: save(ClientDto)");
        return id;
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable String id) {
        logger.info("Start 'DELETE' request: deleteById(String)");
        service.deleteById(UUID.fromString(id));
        logger.info("Completed 'DELETE' request: deleteById(String)");
    }
}