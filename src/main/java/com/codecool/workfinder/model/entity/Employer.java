package com.codecool.workfinder.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
public class Employer {

    @Id
    private String id;

    private String name;

    @Column(unique = true)
    private String email;

    @ManyToMany(mappedBy = "employers", cascade = CascadeType.ALL)
    @JsonIgnoreProperties("employers")
    private List<Job> jobs = new ArrayList<>();
}