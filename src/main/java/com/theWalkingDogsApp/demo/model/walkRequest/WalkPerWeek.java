package com.theWalkingDogsApp.demo.model.walkRequest;

import com.theWalkingDogsApp.demo.model.schedule.WeekDay;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class WalkPerWeek {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private WeekDay weekDay;
    @ElementCollection
    @Column(name = "walking_hour")
    @CollectionTable(joinColumns = @JoinColumn(name = "week_day_walk_id"))
    private List<LocalTime> walkingHours;

    public WalkPerWeek(WeekDay weekDay, List<LocalTime> walkingHours) {
        this.weekDay = weekDay;
        this.walkingHours = walkingHours;

    }
}
