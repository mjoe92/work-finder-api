package com.codecool.workfinder.model.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.Size;
import java.util.UUID;

@Data
public class JobDto {

    @Schema(example = "3fa85f64-5717-4562-b3fc-2c963f66afa6")
    private UUID id;

    @Size(max = 50, message = "Field 'title' size is max. 50!")
    @Schema(description = "Title of Job", example = "Java developer")
    private String title;

    @Size(max = 50, message = "Field 'location' size is max. 50!")
    @Schema(description = "Job location", example = "Budapest")
    private String location;
    @Schema(description = "Job description", example = "Great developer wanted for a idiot job...")
    private String description;
    @Schema(description = "Url on api work site", example = "https://www.google.com/")
    private String url;
}