package com.codecool.workfinder.service;

import com.codecool.workfinder.model.dto.ClientDto;
import com.codecool.workfinder.model.dto.JobDto;
import com.codecool.workfinder.model.entity.Client;
import com.codecool.workfinder.model.entity.Job;
import com.codecool.workfinder.model.mapper.ClientMapper;
import com.codecool.workfinder.model.mapper.JobMapper;
import com.codecool.workfinder.repository.ClientRepository;
import com.codecool.workfinder.repository.JobRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class ClientService extends BaseService<Client, ClientDto, String> {

    private final JobRepository jobRepository;
    private final JobMapper jobMapper;
    @Autowired
    public ClientService(ClientRepository clientRepository,
                         ClientMapper clientMapper,
                         JobRepository jobRepository,
                         JobMapper jobMapper) {
        super(clientRepository, clientMapper);
        this.jobRepository = jobRepository;
        this.jobMapper = jobMapper;
    }

    public void save(ClientDto clientDto) {
        clientDto.generateAndSetUUID();
        Client client = mapper.toEntity(clientDto);
        mapper.logInfo("Completed converting to Client!");
        repository.save(client);
        logInfoViaRepository("Completed accessing repository!");
        logger.info("Completed method: save(Client)!");
    }

    public void addJobForClient(ClientDto clientDto,
                                JobDto jobDto) {

        Client client = mapper.toEntity(clientDto);
        mapper.logInfo("Completed converting to Client!");
        Job job = jobMapper.toEntity(jobDto);
        jobMapper.logInfo("Completing converting to Job");
        client.getJobs().add(job);
        repository.save(client);
        logInfoViaRepository("Completed accessing repository!");
    }

    public Optional<ClientDto> deleteById(String uuid) {
        Client client = repository.findById(uuid).orElse(null);
        if (client == null) {
            return Optional.empty();
        }
        ClientDto clientDto = mapper.toDto(client);
        repository.deleteById(uuid);
        logInfoViaRepository("Completed accessing repository!");
        mapper.logInfo("Completed converting to ClientDto!");
        logger.info("Completed method: deleteById(UUID)!");
        return Optional.of(clientDto);
    }

    private void logInfoViaRepository(String message) {
        ((ClientRepository) repository).logInfo(message);
    }
}