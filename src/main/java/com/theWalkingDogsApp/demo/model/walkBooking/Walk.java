package com.theWalkingDogsApp.demo.model.walkBooking;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
@NoArgsConstructor
public class Walk {
    private LocalDate date;
    private LocalTime time;
    private WalkStatus status;

    public Walk(LocalDate date, LocalTime time, WalkStatus status) {
        this.date = date;
        this.time = time;
        this.status = status;
    }
}
