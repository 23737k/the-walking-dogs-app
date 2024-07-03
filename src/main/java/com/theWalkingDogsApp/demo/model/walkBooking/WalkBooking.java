package com.theWalkingDogsApp.demo.model.walkBooking;

import com.theWalkingDogsApp.demo.model.DogWalker;
import com.theWalkingDogsApp.demo.model.walkRequest.Pet;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class WalkBooking {
    private List<Pet> pets;
    private String phoneNumber;
    private String message;
    private DogWalker dogWalker;
    private List<Walk> walks;

    public WalkBooking(List<Pet> pets, String phoneNumber, String message, DogWalker dogWalker, List<Walk> walks) {
        this.pets = pets;
        this.phoneNumber = phoneNumber;
        this.message = message;
        this.dogWalker = dogWalker;
        this.walks = walks;
    }
}
