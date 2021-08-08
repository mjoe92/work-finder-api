package com.codecool.workfinder.service;

import com.codecool.workfinder.model.mapper.ClientMapper;
import com.codecool.workfinder.repository.ClientRepository;
import com.codecool.workfinder.model.dto.ClientDto;
import com.codecool.workfinder.model.dto.JobDto;
import com.codecool.workfinder.model.entity.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class ClientService extends BaseService<Client, ClientDto, UUID> {

    @Autowired
    public ClientService(ClientRepository clientRepository,
                         ClientMapper clientMapper) {
        super(clientRepository, clientMapper);
    }

    @Override
    public UUID deleteById(UUID uuid) {
        //clientRepository.logMessage("");
        repository.deleteById(uuid);
        return uuid;
    }

    public UUID saveAndReturnId(ClientDto clientDto) {
        Client client = mapper.toEntity(clientDto);
        repository.save(client);
        return client.getId();
    }

    public Optional<JobDto> registerJobForClient(ClientDto clientDto,
                                                 JobDto jobDto) {
        Client client = mapper.toEntity(clientDto);
        //Job job =
        return null;
    }
}