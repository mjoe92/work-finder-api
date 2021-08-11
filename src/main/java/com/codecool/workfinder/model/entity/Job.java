package com.codecool.workfinder.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Data
@Entity
public class Job {

    @Id
    //@GeneratedValue(strategy = GenerationType.IDENTITY, generator = "uuid")
    //@GenericGenerator(name = "uuid", strategy = "uuid2")
    private UUID id;
    private String title;
    private String location;
    private String description;
    private String url;

    @ManyToMany
    @JsonIgnoreProperties("jobs")
    private List<Employer> employers = new ArrayList<>();
    @ManyToMany
    @JsonIgnoreProperties("jobs")
    private List<Client> clients = new ArrayList<>();

}