package com.theWalkingDogsApp.demo.repository.specification;

import com.theWalkingDogsApp.demo.model.pet.DogSize;
import com.theWalkingDogsApp.demo.model.schedule.TimeSlot;
import com.theWalkingDogsApp.demo.model.schedule.WeekDay;
import io.swagger.v3.oas.annotations.Parameter;
import jakarta.validation.constraints.Positive;
import java.util.List;

public record DogWalkerFilter(
    @Parameter(description = "Whether the DogWalker is active or not")
    Boolean active,
    @Parameter(description = "The weekdays on which the DogWalker is available")
    List<WeekDay> weekDays,
    @Parameter(description = "The timeslots during which the DogWalker is available")
    List<TimeSlot> timeSlots,
    @Parameter(description = "Minimum walk rate per hour ")
    @Positive(message = "minRate must be a positive value")
    Integer minRate,
    @Parameter(description = "Maximum walk rate per hour ")
    @Positive(message = "maxRate must be a positive value")
    Integer maxRate,
    @Parameter(description = "Dog sizes allowed by the DogWalker")
    List<DogSize> dogSizes
//    @Positive(message = "serviceRadius must be a positive number")
//    Integer serviceRadius
) {
}
