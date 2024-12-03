package com.theWalkingDogsApp.demo.model.pet;

import com.theWalkingDogsApp.demo.model.dogOwner.DogOwner;
import jakarta.persistence.*;
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
    @ManyToOne
    private DogOwner dogOwner;


    public Pet(String name, String breed, Sex sex, Integer age, double weighInKg) {
        this.name = name;
        this.breed = breed;
        this.sex = sex;
        this.age = age;
        this.weighInKg = weighInKg;
    }


}
