package com.theWalkingDogsApp.demo;

import static com.theWalkingDogsApp.demo.model.schedule.WeekDay.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;

import com.theWalkingDogsApp.demo.model.dogWalker.DogWalker;
import com.theWalkingDogsApp.demo.model.walkBooking.WalkBooking;
import com.theWalkingDogsApp.demo.model.pet.Pet;
import com.theWalkingDogsApp.demo.model.walkRequest.RecurringWalk;
import com.theWalkingDogsApp.demo.model.walkRequest.WalkPerWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;

public class RecurringWalkTest {
  private final List<Pet> pets = new ArrayList<>();
  private final String phoneNumber = "";
  private final String message = "";
  private final DogWalker dogWalker = mock(DogWalker.class);
  private List<WalkPerWeek> walksPerWeekDays;
  private LocalDate startOfService;
  private LocalDate endOfService;
  @Test
  public void testGetWalks(){
    List<LocalTime> walkingHours = List.of(LocalTime.of(8,30), LocalTime.of(15,30));
    WalkPerWeek walksPerWeekDay1 = new WalkPerWeek(MONDAY,walkingHours);
    WalkPerWeek walksPerWeekDay2 = new WalkPerWeek(TUESDAY,walkingHours);
    startOfService = LocalDate.of(2024,7,3);
    endOfService = LocalDate.of(2024,7,24);
    walksPerWeekDays = List.of(walksPerWeekDay1, walksPerWeekDay2);

    RecurringWalk
        walk = new RecurringWalk(pets,phoneNumber,message,dogWalker, walksPerWeekDays,startOfService,endOfService);
    WalkBooking booking = walk.createBooking();
    assertEquals(12, booking.getWalks().size());
    assertEquals(LocalDate.of(2024,7,8), booking.getWalks().get(0).getDate());
  }


}
