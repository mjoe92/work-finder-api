package com.gbsolutions.workfinder.model.api.adzuna;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.gbsolutions.workfinder.model.entity.Job;
import lombok.Data;

import java.util.List;
import java.util.stream.Collectors;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class ApiJobCollection {

    @JsonProperty("results")
    private List<ApiJob> apiJobs;

    public List<Job> toJobList() {
        return apiJobs.stream().map(apiJob -> {
            Job job = new Job();
            job.setTitle(apiJob.getTitle());
            job.setLocation(apiJob.getLocation().getLocationName());
            return job;
        }).collect(Collectors.toList());
    }
}

@Data
class ApiJob {

    private String title;
    private ApiJobLocation location;

}

@Data
class ApiJobLocation {

    @JsonProperty("display_name")
    private String locationName;

}