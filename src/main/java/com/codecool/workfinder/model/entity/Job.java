package com.codecool.workfinder.model.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
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
}