package com.theWalkingDogsApp.demo.model.walkRequest;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Pet {
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
