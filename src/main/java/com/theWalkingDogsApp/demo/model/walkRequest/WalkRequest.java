package com.theWalkingDogsApp.demo.model.walkRequest;

import com.theWalkingDogsApp.demo.model.careGiver.DogWalker;
import com.theWalkingDogsApp.demo.model.walkBooking.WalkBooking;
import jakarta.persistence.CascadeType;
import jakarta.persistence.DiscriminatorColumn;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "type")
public abstract class WalkRequest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @ManyToMany(cascade = CascadeType.ALL)
    protected List<Pet> pets;
    protected String phoneNumber;
    protected String message;
    @ManyToOne
    protected DogWalker dogWalker;

    public WalkRequest(List<Pet> pets, String phoneNumber, String message, DogWalker dogWalker){
        this.pets = pets;
        this.phoneNumber = phoneNumber;
        this.message = message;
        this.dogWalker = dogWalker;
    }
    public abstract WalkBooking createBooking();
}
