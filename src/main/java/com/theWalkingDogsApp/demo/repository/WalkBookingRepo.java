package com.theWalkingDogsApp.demo.repository;

import com.theWalkingDogsApp.demo.model.dogOwner.DogOwner;
import com.theWalkingDogsApp.demo.model.dogWalker.DogWalker;
import com.theWalkingDogsApp.demo.model.walkBooking.WalkBooking;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface WalkBookingRepo extends JpaRepository<WalkBooking,Integer> {
    List<WalkBooking> findWalkBookingByDogOwner(DogOwner dogOwner);
    List<WalkBooking> findWalkBookingByDogWalker(DogWalker dogWalker);
}
