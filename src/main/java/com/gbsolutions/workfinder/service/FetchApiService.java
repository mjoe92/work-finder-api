package com.gbsolutions.workfinder.service;

import com.gbsolutions.workfinder.model.api.adzuna.ApiJobCollection;
import com.gbsolutions.workfinder.model.entity.Job;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Objects;

@Service
public class FetchApiService {

    @Autowired
    private RestTemplate restTemplate;

    @Value("${api}")
    private String api;
    @Value("${api_key}")
    private String apiKey;
    @Value("${api_id}")
    private String apiId;

    public ApiJobCollection getJobCollection() {
        return restTemplate.getForObject(getApiUrl("", ""), ApiJobCollection.class);
    }

    public List<Job> getJobListBy(String title, String location) {
        return Objects.requireNonNull(restTemplate
                .getForObject(getApiUrl(title, location), ApiJobCollection.class))
                .toJobList();
    }

    public String getApiUrl(String title, String location) {
        switch (api) {
            case "adzuna":
                return  "https://api.adzuna.com/v1/api/jobs/gb/search/1?" +
                        "app_id=" + apiId + "&" +
                        "app_key=" + apiKey + "&" +
                        "title_only=" + title + "&" +
                        "where=" + location + "&" +
                        "content-type=application/json";
        }
        return "";
    }
}