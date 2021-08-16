package com.codecool.workfinder.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.*;

@Data
@Schema(description = "A User Object")
public class ClientDto {

    @Size(max = 100, message = "Field 'name' size is max. 100!")
    @NotBlank(message = "Field 'name' mustn't be blanked!")
    @Schema(description = "Name of client", example = "John Doe")
    private String name;
    @Schema(example = "7422ea93-844b-437b-8f40-3c6b82221e14")
    private String id;


    @Email(regexp=".+@.+\\..+", message = "Field 'email' is not valid!")
    @NotBlank(message = "Field 'name' mustn't be blanked!")
    @Schema(description = "Email of client", example = "john.doe@gmail.com")
    private String email;

    @JsonIgnoreProperties("clients")
    private Set<JobDto> jobs = new HashSet<>();

    public void generateAndSetUUID() {
        if (id == null || id.equals("")) {
            setId(String.valueOf(UUID.randomUUID()));
        }
    }
}