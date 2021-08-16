package com.codecool.workfinder.service;

import com.codecool.workfinder.model.dto.JobDto;
import com.codecool.workfinder.model.entity.Employer;
import com.codecool.workfinder.model.entity.Job;
import com.codecool.workfinder.model.mapper.EmployerMapper;
import com.codecool.workfinder.model.mapper.JobMapper;
import com.codecool.workfinder.repository.ClientRepository;
import com.codecool.workfinder.repository.EmployerRepository;
import com.codecool.workfinder.repository.JobRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class JobService extends BaseService<Job, JobDto, String> {

    private final EmployerRepository employerRepository;
    private final ClientRepository clientRepository;
    private final ApiFetchService apiFetchService;
    @Autowired
    public JobService(JobRepository jobRepository,
                      JobMapper jobMapper,
                      EmployerRepository employerRepository,
                      EmployerMapper employerMapper,
                      ClientRepository clientRepository,
                      ApiFetchService apiFetchService) {

        super(jobRepository, jobMapper);
        this.employerRepository = employerRepository;
        this.clientRepository = clientRepository;
        this.apiFetchService = apiFetchService;
    }

    private final String LOCAL_URL_PATH = "http://localhost:8081";

    public List<JobDto> getJobListFromApiBy(String apiName,
                                            String title,
                                            String location,
                                            Long pages) {

        logger.info("");
        title = title == null ? "" : title;
        location = location == null ? "" : location;
        List<Job> jobList = apiFetchService
                .getJobListBy(apiName, title, location, pages);
        apiFetchService.logger.info("");
        List<JobDto> jobDtoList = mapper.toDtoList(jobList);
        mapper.logInfo("");
        return jobDtoList;
    }

    public JobDto assignJobToEmployerThenSave(JobDto jobDto, String employerId) {

        String url = getLocalUrl(jobDto);
        jobDto.setUrl(url);
        //jobDto = (JobDto) fillDtoProperties(jobDto);
        Job job = mapper.toEntity(jobDto);
        mapper.logInfo("");

        Employer employer = employerRepository.getById(employerId);
        employer.getJobs().add(job);
        job.setEmployer(employer);
        repository.save(job);
        employerRepository.save(employer);

        ((JobRepository) repository).logInfo("");
        jobDto = mapper.toDto(job);
        return jobDto;
    }

    private String getLocalUrl(JobDto jobDto) {
        String path = jobDto.getUrl();
        if (path == null || path.equals("")) {
            path = LOCAL_URL_PATH + "/positions?title=" +
                    jobDto.getTitle() + "&location=" + jobDto.getLocation();
        }
        return path;
    }

    public List<JobDto> listJobsBy(String apiName, Long pages, String title, String location) {
        final String titleFilter = (title == null ? "" : title);
        final String locationFilter = (location == null ? "" : location);
        final Long pagesFilter = (pages == null) ? 5 : pages;

        List<JobDto> allJobs = getJobListFromApiBy(apiName, title, location, pagesFilter);
        allJobs.addAll(findAllInRepo().stream()
                .filter(job -> job.getTitle().contains(titleFilter)
                        && job.getLocation().contains(locationFilter))
                .collect(Collectors.toList()));

        return allJobs;
    }

    public Optional<JobDto> deleteById(String id) {
        logger.info("Completed accessing repository '"
                + repository.getClass().getSimpleName() + "'");
        Job job = repository.getById(id);
        clientRepository.findAll().forEach(client -> {
            client.getJobs().remove(job);
            clientRepository.save(client);
        });
        repository.deleteById(id);
        JobDto dto = mapper.toDto(job);
        logger.info("Completed coupling to controller: deleteById()");
        return Optional.of(dto);
    }

    public boolean isValidEmployer(String id) {
        return employerRepository.existsById(id);
    }
}