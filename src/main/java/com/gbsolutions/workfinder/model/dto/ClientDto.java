package com.gbsolutions.workfinder.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.gbsolutions.workfinder.model.entity.Job;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Data
@Schema(description = "A User Object")
public class ClientDto {

    @JsonIgnore
    private UUID id;

    @Size(min = 10, max = 100, message = "Field 'name' size is max. 100!")
    @NotBlank(message = "Field 'name' mustn't be blanked!")
    @Schema(description = "Name of client", example = "John Doe")
    private String name;

    @Email(regexp=".+@.+\\..+", message = "Field 'email' is not valid!")
    @NotBlank(message = "Field 'name' mustn't be blanked!")
    @Schema(description = "email of client", example = "john.doe@gmail.com")
    private String email;

    private List<Job> jobs = new ArrayList<>();
}