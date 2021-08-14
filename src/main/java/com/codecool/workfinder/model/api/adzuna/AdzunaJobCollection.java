package com.codecool.workfinder.model.api.adzuna;

import com.codecool.workfinder.model.entity.Job;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;
import java.util.stream.Collectors;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class AdzunaJobCollection {

    @JsonProperty("results")
    private List<ApiJob> apiJobs;

    public List<Job> toJobList() {
        return apiJobs.stream().map(apiJob -> {
            Job job = new Job();
            job.setId("ADZUNA_" + apiJob.getId());
            job.setCategory(apiJob.getCategory().getLabel());
            job.setTitle(apiJob.getTitle());
            job.setLocation(apiJob.getLocation().getLocationName());
            job.setCreated(apiJob.getCreated());
            job.setDescription(apiJob.getDescription());
            job.setCompany(apiJob.getCompany().getDisplayName());
            job.setContractTime(apiJob.getContractTime());
            job.setUrl(apiJob.getUrl());
            job.setMinSalary(apiJob.getMinSalary());
            job.setMaxSalary(apiJob.getMaxSalary());
            return job;
        }).collect(Collectors.toList());
    }
}

@Data
class ApiJob {

    private String id;
    private String title;
    private Location location;
    @JsonProperty("contract_time")
    private String contractTime;
    private String created;
    private String description;
    @JsonProperty("redirect_url")
    private String url;
    @JsonProperty("salary_min")
    private Integer minSalary;
    @JsonProperty("salary_max")
    private Integer maxSalary;
    private Company company;
    private Category category;

    @Data
    static class Company {

        @JsonProperty("display_name")
        private String displayName;

    }

    @Data
    static class Location {

        @JsonProperty("display_name")
        private String locationName;
        private List<String> area;

    }

    @Data
    static class Category {

        private String label;
        private String tag;

    }
}