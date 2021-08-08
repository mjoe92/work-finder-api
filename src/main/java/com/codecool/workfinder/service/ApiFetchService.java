package com.codecool.workfinder.service;

import com.codecool.workfinder.model.api.adzuna.ApiJobCollection;
import com.codecool.workfinder.repository.ApiRepository;
import com.codecool.workfinder.model.entity.Job;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Objects;

@Service
public class ApiFetchService {

    private final RestTemplate restTemplate;
    private final ApiRepository apiRepository;
/*

    @Value("${api}")
    private String api = "adzuna";
    @Value("${api_key}")
    private String apiKey = "d4ef55f1641ed35a26713baa0fbf57fe";
    @Value("${api_id}")
    private String apiId = "6f3c5d06";
*/

    public ApiFetchService(RestTemplate restTemplate,
                           ApiRepository apiRepository) {
        this.restTemplate = restTemplate;
        this.apiRepository = apiRepository;
    }

    public ApiJobCollection getJobCollection(String apiName) {
        return restTemplate.getForObject(
                getApiUrl(apiName, "", ""),
                ApiJobCollection.class
        );
    }

    public List<Job> getJobListBy(String apiName, String title, String location) {
        return Objects.requireNonNull(restTemplate
                .getForObject(getApiUrl(apiName, title, location), ApiJobCollection.class))
                .toJobList();
    }

    public String getApiUrl(String apiName, String title, String location) {
        //String apiId = apiRepository.getById(apiName).getId();
        //String apiKey = apiRepository.getById(apiName).getKey();
        //mock
        apiName = "adzuna";
        String apiKey = "d4ef55f1641ed35a26713baa0fbf57fe";
        String apiId = "6f3c5d06";
        switch (apiName) {
            case "adzuna":
                return "https://api.adzuna.com/v1/api/jobs/gb/search/1?" +
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