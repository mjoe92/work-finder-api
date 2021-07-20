package com.gbsolutions.workfinder.model.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.Max;
import java.net.URL;

@Data
@Entity
public class Job {

    @Id
    @Max(50)
    private String title;
    @Max(50)
    private String location;

}