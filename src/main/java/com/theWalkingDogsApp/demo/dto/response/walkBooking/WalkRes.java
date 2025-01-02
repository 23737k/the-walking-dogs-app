package com.theWalkingDogsApp.demo.dto.response.walkBooking;

import com.theWalkingDogsApp.demo.model.walkBooking.WalkStatus;
import java.time.LocalDate;
import java.time.LocalTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class WalkRes {
    private Integer id;
    private LocalDate date;
    private LocalTime time;
    private WalkStatus status;
}
