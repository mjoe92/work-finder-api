package com.codecool.workfinder.model.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
public class Client {

    @Id
    private String id;
    private String name;
    @Column(unique = true)
    private String email;
    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.DETACH)
    private Set<Job> jobs = new HashSet<>();
}