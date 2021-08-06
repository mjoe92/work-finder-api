package com.gbsolutions.workfinder.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.Size;
import java.net.URL;

@Data
public class JobDto {

    @JsonIgnore
    private Long id;

    @Size(max = 50, message = "Field 'title' size is max. 50!")
    @Schema(description = "Title of Job", example = "Java developer")
    private String title;

    @Size(max = 50, message = "Field 'location' size is max. 50!")
    @Schema(description = "Job location", example = "Budapest")
    private String location;
    @Schema(description = "Job description", example = "Great developer wanted for a idiot job...")
    private String description;
    @Schema(description = "Url on api work site", example = "Budapest")
    private URL url;
}