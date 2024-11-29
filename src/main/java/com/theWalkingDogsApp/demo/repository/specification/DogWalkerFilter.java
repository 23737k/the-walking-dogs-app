package com.theWalkingDogsApp.demo.repository.specification;

import com.theWalkingDogsApp.demo.model.schedule.TimeSlot;
import com.theWalkingDogsApp.demo.model.schedule.WeekDay;
import java.util.List;

public record DogWalkerFilter(
    Boolean active,
    List<WeekDay> weekDays,
    List<TimeSlot> timeSlots
) {
}
