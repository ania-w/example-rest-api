package com.example.restapi.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModel;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name = "crab")
@ApiModel(description = "This table contains data crabs.")
public class Crab {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name")
    @Size(min=2, message = "Name should have at least 2 characters")
    private String name;

    @Column(name = "species")
    @Size(min=2, message = "Species should have at least 2 characters")
    private String species;

    @ManyToMany
    @JoinTable(
            name="food_list",
            joinColumns = @JoinColumn(name="crabs_id"),
            inverseJoinColumns = @JoinColumn(name="food_id")
    )
    private List<Food> foodList=new ArrayList<>();

    public Crab() {
    }

    public Crab(Integer id, String name, String species, List<Food> foodList) {
        this.id = id;
        this.name = name;
        this.species = species;
        this.foodList = foodList;
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

    public String getSpecies() {
        return species;
    }

    public void setSpecies(String species) {
        this.species = species;
    }

    public List<Food> getFoodList() {
        return foodList;
    }

    public void setFoodList(List<Food> foodList) {
        this.foodList = foodList;
    }
}
