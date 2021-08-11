package com.codecool.workfinder.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Data
@Entity
public class Client {

    @Id
    //@GeneratedValue(strategy = GenerationType.IDENTITY, generator = "uuid")
    //@GenericGenerator(name = "uuid", strategy = "uuid2")
    private UUID id;

    private String name;

    @Column(unique = true)
    private String email;

    @ManyToMany(mappedBy = "clients", cascade = CascadeType.ALL)
    @JsonIgnoreProperties("clients")
    private List<Job> jobs = new ArrayList<>();
}