package com.gbsolutions.workfinder.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.Data;

import javax.validation.constraints.Size;
import java.net.URL;

@Data
public class JobDto {

    @JsonIgnore
    private Long id;

    @Size(max = 50, message = "Field 'title' size is max. 50!")
    private String title;

    @Size(max = 50, message = "Field 'location' size is max. 50!")
    private String location;
    private String description;
    private URL url;

}