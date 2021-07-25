package com.gbsolutions.workfinder.service;

import com.gbsolutions.workfinder.model.dto.ClientDto;
import com.gbsolutions.workfinder.model.entity.Client;
import com.gbsolutions.workfinder.model.mapper.ClientMapper;
import com.gbsolutions.workfinder.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class ClientService extends BaseService<Client, ClientDto, UUID> {

    @Autowired
    public ClientService(ClientRepository repository,
                         ClientMapper clientMapper) {
        super(repository, clientMapper);
    }

    @Override
    public UUID deleteById(UUID uuid) {
        repository.deleteById(uuid);
        return uuid;
    }

    public UUID saveAndReturnId(Client client) {
        repository.save(client);
        return client.getId();
    }
}