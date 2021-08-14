package com.codecool.workfinder.model.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
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
    @OneToMany
    private List<Job> jobs = new ArrayList<>();
}