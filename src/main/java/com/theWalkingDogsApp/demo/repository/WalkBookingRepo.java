package com.theWalkingDogsApp.demo.repository;

import com.theWalkingDogsApp.demo.model.walkBooking.WalkBooking;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WalkBookingRepo extends JpaRepository<WalkBooking,Integer> {
}
