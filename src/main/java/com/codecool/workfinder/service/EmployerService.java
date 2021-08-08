package com.codecool.workfinder.service;

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

    @Override
    public String deleteById(String nanoId) {
        repository.deleteById(nanoId);
        return nanoId;
    }

    public String saveAndReturnId(Employer employer) {
        repository.save(employer);
        return employer.getId();
    }
}