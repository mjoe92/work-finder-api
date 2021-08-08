package com.codecool.workfinder.model.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.net.URL;

@Data
@Entity
public class Api {

    @Id
    private String key;
    private String name;
    private String id;
    private URL url;
}