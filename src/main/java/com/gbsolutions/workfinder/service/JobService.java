package com.gbsolutions.workfinder.service;

import com.gbsolutions.workfinder.model.dto.JobDto;
import com.gbsolutions.workfinder.model.entity.Job;
import com.gbsolutions.workfinder.model.mapper.JobMapper;
import com.gbsolutions.workfinder.repository.EmployerRepository;
import com.gbsolutions.workfinder.repository.JobRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class JobService extends BaseService<Job, JobDto, Long> {

    @Autowired
    private final EmployerRepository employerRepository;
    @Autowired
    private final ApiFetchService apiFetchService;

    @Autowired
    public JobService(JobRepository jobRepository,
                      JobMapper jobMapper,
                      EmployerRepository employerRepository,
                      ApiFetchService apiFetchService) {

        super(jobRepository, jobMapper);
        this.employerRepository = employerRepository;
        this.apiFetchService = apiFetchService;
    }

    public List<JobDto> getJobListFromApiBy(String apiKey,
                                            String title,
                                            String location) {
        title = title == null ? "" : title;
        location = location == null ? "" : location;
        return mapper.toDtoList(apiFetchService
                .getJobListBy(apiKey, title, location));
    }

    public Optional<URL> saveAndReturnUrl(Job job) {
        URL url = null;
        if (job.getUrl() == null) {
            String path = "http://localhost:8080/positions?title=" +
                    job.getTitle() + "&location=" + job.getLocation();
            try {
                url = new URL(path);
            } catch (MalformedURLException e) {
                logger.error("Invalid URL path!");
            }
        }
        job.setUrl(url);
        repository.save(job);
        return Optional.ofNullable(url);
    }

    public List<JobDto> listJobsBy(String apiKey, String title, String location) {
        final String titleFilter = (title == null ? "" : title);
        final String locationFilter = (location == null ? "" : location);

        List<JobDto> allJobs = getJobListFromApiBy(apiKey, title, location);
        allJobs.addAll(findAllInRepo().stream()
                .filter(job -> job.getTitle().contains(titleFilter)
                        && job.getLocation().contains(locationFilter))
                .collect(Collectors.toList()));

        return allJobs;
    }

    public boolean isValidApiKey(String nanoId) {
        return employerRepository.existsById(nanoId);
    }
}