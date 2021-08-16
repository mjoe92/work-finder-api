package com.codecool.workfinder.model.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
public class Job {

    @Id
    private String id;
    private String category;
    private String title;
    private String location;
    private String created;
    private String description;
    private String company;
    private String contractTime;
    private String url;
    private Integer minSalary;
    private Integer maxSalary;
    @ManyToOne(fetch = FetchType.LAZY)
    private Employer employer;
    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.DETACH)
    private List<Client> clientList = new ArrayList<>();

    public void setContractTime(String contractTime) {
        if (contractTime == null || contractTime.equals("")) {
            contractTime = "MISSING INFO!!!";
        }
        this.contractTime = contractTime;
    }
}