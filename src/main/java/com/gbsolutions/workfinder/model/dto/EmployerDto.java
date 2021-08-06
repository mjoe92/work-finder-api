package com.gbsolutions.workfinder.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.gbsolutions.workfinder.model.entity.Job;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Data
public class EmployerDto {

    @JsonIgnore
    private String id;

    @Size(max = 100, message = "Field 'name' size is max. 100!")
    @NotBlank(message = "Field 'name' mustn't be blanked!")
    private String name;

    @Email(message = "Field 'email' is not valid!")
    private String email;

    private List<Job> jobs = new ArrayList<>();
}