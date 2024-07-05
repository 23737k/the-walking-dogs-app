package com.theWalkingDogsApp.demo.model.walkBooking;

import com.theWalkingDogsApp.demo.model.DogWalker;
import com.theWalkingDogsApp.demo.model.walkRequest.Pet;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
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
    @ManyToOne
    private DogWalker dogWalker;
    @OneToMany
    @JoinColumn(name = "walk_booking_id")
    private List<Walk> walks;

    public WalkBooking(List<Pet> pets, String phoneNumber, String message, DogWalker dogWalker, List<Walk> walks) {
        this.pets = pets;
        this.phoneNumber = phoneNumber;
        this.message = message;
        this.dogWalker = dogWalker;
        this.walks = walks;
    }
}
