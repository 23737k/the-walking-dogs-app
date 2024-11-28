package com.theWalkingDogsApp.demo.model.walkRequest;

import com.theWalkingDogsApp.demo.model.dogOwner.DogOwner;
import com.theWalkingDogsApp.demo.model.dogWalker.DogWalker;
import com.theWalkingDogsApp.demo.model.pet.Pet;
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
import java.util.List;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "type")
public abstract class WalkRequest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @ManyToMany(cascade = CascadeType.PERSIST)
    protected List<Pet> pets;
    protected String phoneNumber;
    protected String message;
    @ManyToOne
    protected DogWalker dogWalker;
    @ManyToOne
    protected DogOwner dogOwner;

    public WalkRequest(List<Pet> pets, String phoneNumber, String message, DogWalker dogWalker, DogOwner dogOwner){
        this.pets = pets;
        this.phoneNumber = phoneNumber;
        this.message = message;
        this.dogWalker = dogWalker;
        this.dogOwner = dogOwner;
    }
    public abstract WalkBooking createBooking();
}
