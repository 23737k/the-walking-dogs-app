package com.theWalkingDogsApp.demo;

import static com.theWalkingDogsApp.demo.model.schedule.TimeSlot.*;
import static com.theWalkingDogsApp.demo.model.schedule.WeekDay.*;
import com.theWalkingDogsApp.demo.model.schedule.DailyAvailability;
import com.theWalkingDogsApp.demo.model.schedule.Schedule;
import com.theWalkingDogsApp.demo.model.schedule.TimeSlot;
import java.time.LocalDate;
import java.util.List;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;


public class ScheduleTest {
  static Schedule schedule;


  @BeforeAll
  static void setUpBeforeClass() throws Exception {

    List<TimeSlot> availableTimeSlots = List.of(MORNING,AFTERNOON);
    LocalDate unavailableDate = LocalDate.of(2024,7,2);

    schedule = Schedule.builder()
        .unavailableDates(List.of(unavailableDate))
        .dailyAvailabilities(List.of(
            new DailyAvailability(MONDAY, availableTimeSlots),
            new DailyAvailability(TUESDAY,availableTimeSlots)
        ))
        .build();
  }

  @Test
  public void testUnavailableDate(){
    LocalDate date = LocalDate.of(2024,7,2);
    assertFalse(schedule.isAvailable(date));
  }

  @Test
  public void testAvailableDate(){
    LocalDate date1 = LocalDate.of(2024,7,1);
    LocalDate date2 = LocalDate.of(2024,7,23);
    assertTrue(schedule.isAvailable(date1));
    assertTrue(schedule.isAvailable(date2));
  }

  @Test
  public void testAvailableWeekDay(){
    assertFalse(schedule.isAvailableForWeekDay(SATURDAY));
    assertTrue(schedule.isAvailableForWeekDay(MONDAY));
  }

  @Test
  public void testAvailabilityFromStartDateAndWeekDayList(){
    assertTrue(schedule.isAvailable(LocalDate.of(2024,7,3),List.of(MONDAY,TUESDAY)));
    assertFalse(schedule.isAvailable(LocalDate.of(2024,7,1),List.of(MONDAY,TUESDAY)));
    assertFalse(schedule.isAvailable(LocalDate.of(2024,7,3),List.of(MONDAY,TUESDAY,FRIDAY)));
    assertTrue(schedule.isAvailable(LocalDate.of(2024,7,3),List.of(MONDAY)));
  }


}
