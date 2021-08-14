package com.codecool.workfinder.model.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.util.UUID;

@Data
@Entity
public class Job {

    @Id
    //@GeneratedValue(strategy = GenerationType.IDENTITY, generator = "uuid")
    //@GenericGenerator(name = "uuid", strategy = "uuid2")
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
    @ManyToOne
    private Employer employer = new Employer();

    public void setContractTime(String contractTime) {
        if (contractTime == null || contractTime.equals("")) {
            contractTime = "MISSING INFO!!!";
        }
        this.contractTime = contractTime;
    }

    public void generateAndSetUUID() {
        if (id == null || id.equals("")) {
            setId(String.valueOf(UUID.randomUUID()));
        }
    }
}