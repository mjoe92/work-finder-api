package com.gbsolutions.workfinder.service;

import com.gbsolutions.workfinder.model.entity.Client;
import com.gbsolutions.workfinder.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ClientService {

    @Autowired
    private ClientRepository repository;

    public List<Client> findAll() {
        return repository.findAll();
    }

    public Client findById(UUID uuid) {
        return repository.findById(uuid).orElse(null);
    }

    public void deleteById(UUID uuid) {
        repository.deleteById(uuid);
    }

    public UUID saveAndReturnId(Client client) {
        repository.save(client);
        return client.getId();
    }
}