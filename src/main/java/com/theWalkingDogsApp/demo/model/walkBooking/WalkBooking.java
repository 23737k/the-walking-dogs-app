package com.theWalkingDogsApp.demo.model.walkBooking;

import com.theWalkingDogsApp.demo.model.dogOwner.DogOwner;
import com.theWalkingDogsApp.demo.model.dogWalker.DogWalker;
import com.theWalkingDogsApp.demo.model.pet.Pet;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@Entity
public class WalkBooking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @ManyToMany
    private List<Pet> pets;
    private String phoneNumber;
    private String message;
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "walk_booking_id")
    private List<Walk> walks;
    @ManyToOne
    private DogWalker dogWalker;
    @ManyToOne
    private DogOwner dogOwner;

    public WalkBooking(List<Pet> pets, String phoneNumber, String message, List<Walk> walks, DogOwner dogOwner, DogWalker dogWalker) {
        this.pets = pets;
        this.phoneNumber = phoneNumber;
        this.message = message;
        this.walks = walks;
        this.dogOwner = dogOwner;
        this.dogWalker = dogWalker;
    }
}
