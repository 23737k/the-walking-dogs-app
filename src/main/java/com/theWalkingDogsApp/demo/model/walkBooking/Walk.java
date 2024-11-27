package com.theWalkingDogsApp.demo.model.walkBooking;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
@NoArgsConstructor
@Entity
public class Walk {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private LocalDate date;
    private LocalTime time;
    @Enumerated(EnumType.STRING)
    private WalkStatus status;

    public Walk(LocalDate date, LocalTime time, WalkStatus status) {
        this.date = date;
        this.time = time;
        this.status = status;
    }
}
