package com.theWalkingDogsApp.demo.repository;

import com.theWalkingDogsApp.demo.model.walkBooking.WalkBooking;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface WalkBookingRepo extends JpaRepository<WalkBooking,Integer> {
}
