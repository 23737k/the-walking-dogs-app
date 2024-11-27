package com.theWalkingDogsApp.demo.dto.response.schedule;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@Data
@AllArgsConstructor
@Builder
public class ScheduleRes {
    private List<DailyAvailabilityRes> dailyAvailabilities;
    private Set<LocalDate> unavailableDates;
}
