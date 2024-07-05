package com.theWalkingDogsApp.demo.model.walkRequest;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
public class Pet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private String breed;
    private double weighInKg;
    private Sex sex;
    private Integer age;


    public Pet(String name, String breed, Sex sex, Integer age, double weighInKg) {
        this.name = name;
        this.breed = breed;
        this.sex = sex;
        this.age = age;
        this.weighInKg = weighInKg;
    }


}
