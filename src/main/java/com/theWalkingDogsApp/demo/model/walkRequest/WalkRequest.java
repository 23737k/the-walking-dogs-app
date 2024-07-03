package com.theWalkingDogsApp.demo.model.walkRequest;

import com.theWalkingDogsApp.demo.model.DogWalker;
import com.theWalkingDogsApp.demo.model.walkBooking.WalkBooking;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public abstract class WalkRequest {
    protected List<Pet> pets;
    protected String phoneNumber;
    protected String message;
    protected DogWalker dogWalker;

    public WalkRequest(List<Pet> pets, String phoneNumber, String message, DogWalker dogWalker){
        this.pets = pets;
        this.phoneNumber = phoneNumber;
        this.message = message;
        this.dogWalker = dogWalker;
    }
    public abstract WalkBooking createBooking();
}
