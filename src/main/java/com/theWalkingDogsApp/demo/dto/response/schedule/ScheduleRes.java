package com.theWalkingDogsApp.demo.dto.response.schedule;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class ScheduleRes {
    private List<DailyAvailabilityRes> dailyAvailabilities;
    private Set<LocalDate> unavailableDates;
}
