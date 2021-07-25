package com.gbsolutions.workfinder.controller;

import com.gbsolutions.workfinder.model.dto.ClientDto;
import com.gbsolutions.workfinder.model.entity.Client;
import com.gbsolutions.workfinder.service.ClientService;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.UUID;

@RestController
@RequestMapping("/clients")
public class ClientController extends BaseController<Client, ClientDto, UUID, ClientService> {

    protected ClientController(ClientService service) {
        super(service);
    }

    @GetMapping("/{id}")
    public ClientDto findById(@PathVariable String id) {
        logger.info("Completed 'GET' request: findById" +
                "("+ id.getClass().getSimpleName() + ")");
        return service.findById(UUID.fromString(id));
    }

    @PostMapping
    public UUID saveAndReturnId(@Valid @RequestBody Client client) {
        logger.info("Completed 'POST' request: save(client)");
        return service.saveAndReturnId(client);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable String id) {
        service.deleteById(UUID.fromString(id));
    }
}