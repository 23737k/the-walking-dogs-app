package com.theWalkingDogsApp.demo;

import static com.theWalkingDogsApp.demo.model.schedule.TimeSlot.*;
import static com.theWalkingDogsApp.demo.model.schedule.WeekDay.*;
import com.theWalkingDogsApp.demo.model.schedule.DailyAvailability;
import com.theWalkingDogsApp.demo.model.schedule.Schedule;
import com.theWalkingDogsApp.demo.model.schedule.TimeSlot;
import java.time.LocalDate;
import java.util.List;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertTrue;


public class ScheduleTest {

  @Test
  public void checkAvailability(){
    List<TimeSlot> availableTimeSlots = List.of(MORNING,AFTERNOON);
    Schedule schedule = new Schedule();
    schedule.addUnavailableDate(LocalDate.of(2024,6,30));
    schedule.setDailyAvailabilities(List.of(
        new DailyAvailability(MONDAY, availableTimeSlots),
        new DailyAvailability(TUESDAY,availableTimeSlots)
    ));

    assertTrue(schedule.isAvailable());


  }
}
