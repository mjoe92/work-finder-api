package com.codecool.workfinder.service;

import com.codecool.workfinder.model.dto.ClientDto;
import com.codecool.workfinder.model.dto.JobDto;
import com.codecool.workfinder.model.entity.Client;
import com.codecool.workfinder.model.mapper.ClientMapper;
import com.codecool.workfinder.repository.ClientRepository;
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

    public ClientDto deleteById(UUID uuid) {
        repository.deleteById(uuid);
        Client client = repository.getById(uuid);
        logInfoViaRepository("Completed accessing repository!");
        ClientDto clientDto = mapper.toDto(client);
        mapper.logInfo("Completed converting to ClientDto!");
        logger.info("Completed method: deleteById(UUID)!");
        return clientDto;
    }

    public void save(ClientDto clientDto) {
        clientDto.setId(UUID.randomUUID());
        Client client = mapper.toEntity(clientDto);
        mapper.logInfo("Completed converting to Client!");
        repository.save(client);
        logInfoViaRepository("Completed method: save(Client)!");
    }

    public Optional<JobDto> registerJobForClient(ClientDto clientDto,
                                                 JobDto jobDto) {
        Client client = mapper.toEntity(clientDto);
        //Job job =
        return null;
    }

    private void logInfoViaRepository(String message) {
        ((ClientRepository) repository).logMessage(message);
    }
}