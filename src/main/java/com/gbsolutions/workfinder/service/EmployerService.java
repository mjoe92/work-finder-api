package com.gbsolutions.workfinder.service;

import com.gbsolutions.workfinder.model.dto.EmployerDto;
import com.gbsolutions.workfinder.model.entity.Employer;
import com.gbsolutions.workfinder.model.mapper.EmployerMapper;
import com.gbsolutions.workfinder.repository.EmployerRepository;
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