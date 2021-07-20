package com.gbsolutions.workfinder.controller;

import com.gbsolutions.workfinder.model.entity.Client;
import com.gbsolutions.workfinder.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.UUID;
import java.util.List;

@RestController
@RequestMapping("/clients")
public class ClientController {

    @Autowired
    private ClientService service;

    @GetMapping
    public List<Client> findAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public Client findById(@PathVariable String id) {
        return service.findById(UUID.fromString(id));
    }

    @PostMapping
    public UUID clientRegister(@Valid @RequestBody Client client) {
        return service.saveAndReturnId(client);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable String id) {
        service.deleteById(UUID.fromString(id));
    }

}