package com.codecool.workfinder.model.api.jooble;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class JoobleJobCollection {

    @JsonProperty("")
    private List<ApiJob> apiJobs;

}

@Data
class ApiJob {

    private String title;

}