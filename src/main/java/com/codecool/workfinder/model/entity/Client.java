package com.codecool.workfinder.model.entity;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Data
@Entity
public class Client {

    private String name;
    @Id
    //@GeneratedValue(strategy = GenerationType.IDENTITY, generator = "uuid")
    //@GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;


    @Column(unique = true)
    private String email;

    @OneToMany
    private List<Job> jobs = new ArrayList<>();
}