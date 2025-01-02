package com.theWalkingDogsApp.demo.model.walkBooking;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import java.time.LocalDate;
import java.time.LocalTime;
import lombok.Data;
import lombok.NoArgsConstructor;

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
