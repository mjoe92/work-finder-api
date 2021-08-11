package com.codecool.workfinder.model.api.adzuna;

import com.codecool.workfinder.logger.ConsoleLogger;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.codecool.workfinder.logger.PhaseLogger;
import com.codecool.workfinder.model.entity.Job;
import lombok.Data;

import java.net.MalformedURLException;
import java.net.URL;
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
            job.setTitle(apiJob.getTitle());
            job.setDescription(apiJob.getDescription());
            job.setLocation(apiJob.getLocation().getLocationName());
            try {
                job.setUrl(new URL(apiJob.getUrl()).toString());
            } catch (MalformedURLException e) {
                ConsoleLogger logger = new PhaseLogger(this.getClass());
                logger.error("Invalid URL!");
            }
            return job;
        }).collect(Collectors.toList());
    }
}

@Data
class ApiJob {

    private String title;
    private ApiJobLocation location;

    private String description;
    @JsonProperty("redirect_url")
    private String url;

}

@Data
class ApiJobLocation {

    @JsonProperty("display_name")
    private String locationName;

}