package com.codecool.workfinder.service;

import com.aventrix.jnanoid.jnanoid.NanoIdUtils;
import com.codecool.workfinder.repository.ClientRepository;
import com.codecool.workfinder.repository.EmployerRepository;
import com.codecool.workfinder.model.dto.EmployerDto;
import com.codecool.workfinder.model.entity.Employer;
import com.codecool.workfinder.model.mapper.EmployerMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmployerService extends BaseService<Employer, EmployerDto, String> {

    @Autowired
    public EmployerService(EmployerRepository repository,
                           EmployerMapper employerMapper) {
        super(repository, employerMapper);
    }

    public EmployerDto deleteById(String nanoId) {
        repository.deleteById(nanoId);
        Employer employer = repository.getById(nanoId);
        logInfoViaRepository("Completed accessing repository!");
        EmployerDto employerDto = mapper.toDto(employer);
        mapper.logInfo("Completed converting to EmployerDto!");
        logger.info("Completed method: deleteById(String)!");
        return employerDto;
    }

    public void save(EmployerDto employerDto) {
        employerDto.setId(NanoIdUtils.randomNanoId());
        Employer employer = mapper.toEntity(employerDto);
        repository.save(employer);
    }

    private void logInfoViaRepository(String message) {
        ((EmployerRepository) repository).logMessage(message);
    }
}