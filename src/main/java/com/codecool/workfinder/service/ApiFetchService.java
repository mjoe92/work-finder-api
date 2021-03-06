package com.codecool.workfinder.service;

import com.codecool.workfinder.logger.ConsoleLogger;
import com.codecool.workfinder.logger.PhaseLogger;
import com.codecool.workfinder.model.api.adzuna.AdzunaJobCollection;
import com.codecool.workfinder.model.entity.Job;
import com.codecool.workfinder.repository.ApiRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class ApiFetchService {

    private final RestTemplate restTemplate;
    private final ApiRepository apiRepository;
    protected final ConsoleLogger logger;

    public ApiFetchService(RestTemplate restTemplate,
                           ApiRepository apiRepository) {
        this.restTemplate = restTemplate;
        this.apiRepository = apiRepository;
        this.logger = new PhaseLogger(this.getClass());
    }

    public Object getJoobleJobCollection(String title, String location) {
        return restTemplate.getForObject(
                getApiUrl("jooble", title, location, 0L),
                Object.class
        );
    }

    public AdzunaJobCollection getAdzunaJobCollection(String title, String location, Long pages) {
        return restTemplate.getForObject(
                getApiUrl("adzuna", title, location, pages),
                AdzunaJobCollection.class
        );
    }

    public List<Job> getJobListBy(String apiName,
                                  String title,
                                  String location,
                                  Long pages) {

        logger.info("");
        AdzunaJobCollection jobCollection = getAdzunaJobCollection(title, location, pages);
        List<Job> jobs = jobCollection.toJobList();
        logger.info("");
        return jobs;
    }

    public String getApiUrl(String apiName, String title, String location, Long page) {
        String apiId = apiRepository.getByName(apiName).getId();
        String apiKey = apiRepository.getByName(apiName).getKey();
        switch (apiName) {
            case "adzuna":
                return "https://api.adzuna.com/v1/api/jobs/gb/search/"+ page + "?" +
                        "app_id=" + apiId + "&" +
                        "app_key=" + apiKey + "&" +
                        "title_only=" + title + "&" +
                        "where=" + location + "&" +
                        "content-type=application/json";
            case "jooble":
                return "https://jooble.org/api/" +
                        apiKey;
        }
        return null;
    }
}