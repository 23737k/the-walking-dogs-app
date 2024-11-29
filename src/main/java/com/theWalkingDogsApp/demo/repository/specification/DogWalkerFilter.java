package com.theWalkingDogsApp.demo.repository.specification;

import com.theWalkingDogsApp.demo.model.pet.DogSize;
import com.theWalkingDogsApp.demo.model.schedule.TimeSlot;
import com.theWalkingDogsApp.demo.model.schedule.WeekDay;
import jakarta.validation.constraints.Positive;
import java.util.List;

public record DogWalkerFilter(
    Boolean active,
    List<WeekDay> weekDays,
    List<TimeSlot> timeSlots,
    @Positive(message = "minRate must be a positive value")
    Integer minRate,
    @Positive(message = "maxRate must be a positive value")
    Integer maxRate,
    List<DogSize> dogSizes
//    @Positive(message = "serviceRadius must be a positive number")
//    Integer serviceRadius
) {
}
