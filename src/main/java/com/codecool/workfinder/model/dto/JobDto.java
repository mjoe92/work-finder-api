package com.codecool.workfinder.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Data
public class JobDto {

    @Schema(example = "3fa85f64-5717-4562-b3fc-2c963f66afa6")
    private String id;

    @Schema(description = "Job position type", example = "java-developer")
    private String category;

    @Size(max = 50, message = "Field 'title' size is max. 50!")
    @Schema(description = "Title of Job", example = "Java developer")
    private String title;

    @Size(max = 50, message = "Field 'location' size is max. 50!")
    @Schema(description = "Job location", example = "Budapest")
    private String location;

    @Schema(description = "Time of posting job", example = "2021-01-01THH:mm:ddZ")
    private String created;

    @Schema(description = "Job description", example = "Great developer wanted for a idiot job...")
    private String description;

    @Schema(description = "Company name", example = "Codecool Kft.")
    private String company;

    @JsonProperty("contract_time")
    @Schema(description = "Contract type", example = "Full-time, part-time, internship etc.")
    private String contractTime;

    @Schema(description = "URL on api work site", example = "https://www.google.com/")
    private String url;

    @JsonProperty("min_salary")
    @Schema(description = "Salary bottom limit", example = "20000")
    private Integer minSalary;

    @JsonProperty("max_salary")
    @Schema(description = "Salary top limit", example = "22000")
    private Integer maxSalary;

    @JsonProperty("employer")
    @JsonIgnoreProperties({"jobs"})
    private EmployerDto employerDto;

    @JsonProperty("clients")
    @JsonIgnoreProperties({"jobs"})
    private List<JobDto> jobDtoList = new ArrayList<>();
}