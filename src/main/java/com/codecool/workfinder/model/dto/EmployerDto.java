package com.codecool.workfinder.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonIgnoreType;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Data
public class EmployerDto {

    @Schema(example = "V1StGXR8_Z5jdHi6B-myT")
    private String id;

    @Size(max = 100, message = "Field 'name' size is max. 100!")
    @NotBlank(message = "Field 'name' mustn't be blanked!")
    @Schema(description = "Name of employer", example = "Jane Doe")
    private String name;

    @Schema(description = "Name of company!", example = "Codecool Kft.")
    private String company;

    @Email(message = "Field 'email' is not valid!")
    @Schema(description = "Email address of employer", example = "jane.doe@gmail.com")
    private String email;

    private List<JobDto> jobs = new ArrayList<>();
}