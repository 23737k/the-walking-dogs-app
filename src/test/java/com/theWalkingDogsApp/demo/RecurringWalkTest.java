package com.theWalkingDogsApp.demo;

import static com.theWalkingDogsApp.demo.model.schedule.WeekDay.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;

import com.theWalkingDogsApp.demo.model.careGiver.DogWalker;
import com.theWalkingDogsApp.demo.model.walkBooking.WalkBooking;
import com.theWalkingDogsApp.demo.model.walkRequest.Pet;
import com.theWalkingDogsApp.demo.model.walkRequest.RecurringWalk;
import com.theWalkingDogsApp.demo.model.walkRequest.WalksPerWeekDay;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;

public class RecurringWalkTest {
  private List<Pet> pets = new ArrayList<>();
  private String phoneNumber = "";
  private String message = "";
  private DogWalker dogWalker = mock(DogWalker.class);
  private List<WalksPerWeekDay> walksPerWeekDays;
  private LocalDate startOfService;
  private LocalDate endOfService;
  @Test
  public void testGetWalks(){
    List<LocalTime> walkingHours = List.of(LocalTime.of(8,30), LocalTime.of(15,30));
    WalksPerWeekDay walksPerWeekDay1 = new WalksPerWeekDay(MONDAY,walkingHours);
    WalksPerWeekDay walksPerWeekDay2 = new WalksPerWeekDay(TUESDAY,walkingHours);
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
