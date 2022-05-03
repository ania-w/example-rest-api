package com.example.restapi.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModel;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "food")
@ApiModel(description = "This table contains data about favourite crab food.")
public class Food {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name")
    @Size(min=2, message = "Name should have at least 2 characters")
    private String name;

    @ManyToMany(mappedBy = "foodList")
    @JsonIgnore
    List<Crab> crabs=new ArrayList<>();

    public Food() {
    }

    public Food(Integer id, String name, List<Crab> crabs) {
        this.id = id;
        this.name = name;
        this.crabs = crabs;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Crab> getCrabs() {
        return crabs;
    }

    public void setCrabs(List<Crab> crabs) {
        this.crabs = crabs;
    }
}
