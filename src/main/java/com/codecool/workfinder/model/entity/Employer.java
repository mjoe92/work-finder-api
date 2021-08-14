package com.codecool.workfinder.model.entity;

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
    private String company;
    @Column(unique = true)
    private String email;
    @OneToMany(orphanRemoval = true, cascade = CascadeType.REMOVE, fetch = FetchType.LAZY, mappedBy = "employer")
    private List<Job> jobs = new ArrayList<>();
}