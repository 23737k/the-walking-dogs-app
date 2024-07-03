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

    //Disponibilidad: Todos los lunes y martes, mañana y tarde. Excepto el 2024-07-02 (Martes)
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
    assertFalse(schedule.isAvailable(date,MORNING));
  }

  @Test
  public void testAvailableDate(){
    LocalDate date = LocalDate.of(2024,7,1);
    assertTrue(schedule.isAvailable(date,MORNING));
  }

  @Test
  public void testUnavailableTimeSlot(){
    LocalDate date = LocalDate.of(2024,7,16); //lunes
    assertFalse(schedule.isAvailable(date,EVENING));
  }

  @Test
  public void testUnavailableWeekDay(){
    LocalDate date = LocalDate.of(2024,7,3);
    assertFalse(schedule.isAvailable(date,MORNING));
  }

}
