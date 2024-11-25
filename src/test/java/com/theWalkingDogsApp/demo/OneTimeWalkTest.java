package com.theWalkingDogsApp.demo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;

import com.theWalkingDogsApp.demo.model.dogWalker.DogWalker;
import com.theWalkingDogsApp.demo.model.walkBooking.WalkBooking;
import com.theWalkingDogsApp.demo.model.walkRequest.OneTimeWalk;
import com.theWalkingDogsApp.demo.model.pet.Pet;
import com.theWalkingDogsApp.demo.model.walkRequest.WalkPerDate;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;

public class OneTimeWalkTest {

  @Test
  public void testCreateBooking(){
    List<Pet> pets = new ArrayList<>();
    String phoneNumber = "";
    String message = "";
    DogWalker dogWalker = mock(DogWalker.class);
    List<LocalTime> walkingHours = List.of(LocalTime.of(10, 0), LocalTime.of(13, 0), LocalTime.of(20, 0));
    WalkPerDate walkPerDate1 = new WalkPerDate(LocalDate.of(2024,7,10),walkingHours);
    WalkPerDate walkPerDate2 = new WalkPerDate(LocalDate.of(2024,7,11),walkingHours);
    WalkPerDate walkPerDate3 = new WalkPerDate(LocalDate.of(2024,7,12),walkingHours);

    List<WalkPerDate> walkPerDates = List.of(walkPerDate1, walkPerDate2, walkPerDate3);

    OneTimeWalk
        oneTimeWalk = new OneTimeWalk(pets,phoneNumber,message,dogWalker, walkPerDates);
    WalkBooking booking = oneTimeWalk.createBooking();

    assertEquals(9, booking.getWalks().size());
    assertTrue(booking.getWalks().stream().anyMatch(w -> w.getDate().equals(walkPerDate1.getDate())));
  }

}
