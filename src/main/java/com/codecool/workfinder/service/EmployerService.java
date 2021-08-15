package com.codecool.workfinder.service;

import com.codecool.workfinder.model.dto.EmployerDto;
import com.codecool.workfinder.model.dto.JobDto;
import com.codecool.workfinder.model.entity.Employer;
import com.codecool.workfinder.model.entity.Job;
import com.codecool.workfinder.model.mapper.EmployerMapper;
import com.codecool.workfinder.model.mapper.JobMapper;
import com.codecool.workfinder.repository.EmployerRepository;
import com.codecool.workfinder.repository.JobRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class EmployerService extends BaseService<Employer, EmployerDto, String> {

    private final JobRepository jobRepository;
    private final JobMapper jobMapper;
    @Autowired
    public EmployerService(EmployerRepository repository,
                           EmployerMapper employerMapper,
                           JobRepository jobRepository,
                           JobMapper jobMapper) {
        super(repository, employerMapper);
        this.jobRepository = jobRepository;
        this.jobMapper = jobMapper;
    }

    public Optional<EmployerDto> deleteById(String nanoId) {
        Employer employer = repository.findById(nanoId).orElse(null);
        if (employer == null) {
            return Optional.empty();
        }
        //deleteAllJobsOfEmployers(nanoId);
        repository.deleteById(nanoId);
        logInfoViaRepository("Completed accessing repository!");

        EmployerDto employerDto = mapper.toDto(employer);
        mapper.logInfo("Completed converting to EmployerDto!");
        logger.info("Completed method: deleteById(String)!");
        return Optional.of(employerDto);
    }

    private void deleteAllJobsOfEmployers(String id) {
        jobRepository.deleteAllByEmployer_Id(id);
    }

    public void save(EmployerDto employerDto) {
/*        if (employerDto.getId() == null) {
            employerDto.setId(NanoIdUtils.randomNanoId());
        }*/
        Employer employer = mapper.toEntity(employerDto);
        mapper.logInfo("Completed converting to Employer!");
        employer.getJobs().forEach(job -> {
            //job.generateAndSetUUID();
            jobRepository.save(job);
            jobRepository.logInfo("Completed accessing repository!");
        });
        repository.save(employer);
        logInfoViaRepository("Completed accessing repository!");
        logger.info("Completed method: save(Employer)!");
    }

    private void logInfoViaRepository(String message) {
        ((EmployerRepository) repository).logInfo(message);
    }

    public boolean isEmployerRegistered(String employerId) {
        return repository.existsById(employerId);
    }

    public void addJobToEmployer(JobDto jobDto, String employerId) {
        Job job = jobMapper.toEntity(jobDto);
        jobMapper.logInfo("Completed converting to Job!");
        Employer employer = repository.getById(employerId);
        logInfoViaRepository("Completed accessing repository!");
        employer.getJobs().add(job);
        jobRepository.save(job);
        jobRepository.logInfo("Completed accessing repository!");
        logger.info("Completed method: createJobToEmployer(JobDto)!");
    }
}