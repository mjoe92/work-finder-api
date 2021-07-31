package com.gbsolutions.workfinder.model.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@Entity
public class Employer {

    @Id
    private String id;

    private String name;

    @Column(unique = true)
    private String email;

}