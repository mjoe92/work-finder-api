package com.gbsolutions.workfinder.model.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.net.URL;

@Data
@Entity
public class Api {

    private String name;
    @Id
    private String key;
    private String id;
    private URL url;
}