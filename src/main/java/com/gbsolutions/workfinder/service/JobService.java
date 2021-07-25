package com.gbsolutions.workfinder.service;

import com.gbsolutions.workfinder.model.dto.JobDto;
import com.gbsolutions.workfinder.model.entity.Job;
import com.gbsolutions.workfinder.model.mapper.ClientMapper;
import com.gbsolutions.workfinder.model.mapper.JobMapper;
import com.gbsolutions.workfinder.repository.ClientRepository;
import com.gbsolutions.workfinder.repository.JobRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class JobService extends BaseService<Job, JobDto, Long> {

    @Autowired
    private final ClientRepository clientRepository;

    @Autowired
    private final FetchApiService apiService;

    @Autowired
    public JobService(JobRepository jobRepository,
                      JobMapper jobMapper,
                      ClientRepository clientRepository,
                      FetchApiService apiService) {

        super(jobRepository, jobMapper);
        this.clientRepository = clientRepository;
        this.apiService = apiService;
    }

    public List<JobDto> getJobListFromApiBy(String title, String location) {
        title = title == null ? "" : title;
        location = location == null ? "" : location;
        return mapper.toDtoList(apiService.getJobListBy(title, location));
    }

    public Optional<URL> saveAndReturnUrl(Job job) {
        repository.save(job);
        URL url = null;
        try {
            url = new URL("");
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return Optional.ofNullable(url);
    }

    public List<JobDto> listJobsBy(String title, String location) {
        final String titleFilter = (title == null ? "" : title);
        final String locationFilter = (location == null ? "" : location);


        List<JobDto> allJobs = getJobListFromApiBy(title, location);

        return findAll().stream()
                .filter(job -> job.getTitle().contains(titleFilter)
                        && job.getLocation().contains(locationFilter))
                .collect(Collectors.toList());
    }

    public boolean isValidApiKey(UUID uuid) {
        return clientRepository.existsById(uuid);
    }

    public void deleteAllBy(String title) {
        repository.findAll().forEach(job -> {
            if (job.getTitle().equals(title)) {
                repository.delete(job);
            }
        });
    }
}