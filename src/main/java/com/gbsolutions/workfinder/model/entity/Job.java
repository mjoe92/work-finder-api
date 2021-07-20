package com.gbsolutions.workfinder.model.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.Size;
import java.net.URL;

@Data
@Entity
public class Job {

    @Id
    @Size(max = 50, message = "Field 'title' size is max. 50!")
    private String title;
    @Size(max = 50, message = "Field 'location' size is max. 50!")
    private String location;

    private URL url;

}