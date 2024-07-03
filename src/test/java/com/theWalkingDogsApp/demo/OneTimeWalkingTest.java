package com.theWalkingDogsApp.demo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;

import com.theWalkingDogsApp.demo.model.DogWalker;
import com.theWalkingDogsApp.demo.model.walkBooking.WalkBooking;
import com.theWalkingDogsApp.demo.model.walkRequest.OneTimeWalking;
import com.theWalkingDogsApp.demo.model.walkRequest.Pet;
import com.theWalkingDogsApp.demo.model.walkRequest.SingleDayWalk;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;

public class OneTimeWalkingTest {

  @Test
  public void testCreateBooking(){
    List<Pet> pets = new ArrayList<>();
    String phoneNumber = "";
    String message = "";
    DogWalker dogWalker = mock(DogWalker.class);
    List<LocalTime> walkingHours = List.of(LocalTime.of(10, 0), LocalTime.of(13, 0), LocalTime.of(20, 0));
    SingleDayWalk singleDayWalk1 = new SingleDayWalk(LocalDate.of(2024,7,10),walkingHours);
    SingleDayWalk singleDayWalk2 = new SingleDayWalk(LocalDate.of(2024,7,11),walkingHours);
    SingleDayWalk singleDayWalk3 = new SingleDayWalk(LocalDate.of(2024,7,12),walkingHours);

    List<SingleDayWalk> singleDayWalks = List.of(singleDayWalk1, singleDayWalk2, singleDayWalk3);

    OneTimeWalking oneTimeWalking = new OneTimeWalking(pets,phoneNumber,message,dogWalker,singleDayWalks);
    WalkBooking booking = oneTimeWalking.createBooking();

    assertEquals(9, booking.getWalks().size());
    assertTrue(booking.getWalks().stream().anyMatch(w -> w.getDate().equals(singleDayWalk1.getDate())));
  }

}
