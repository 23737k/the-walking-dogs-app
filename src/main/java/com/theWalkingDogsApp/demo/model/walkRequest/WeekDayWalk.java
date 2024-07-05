package com.theWalkingDogsApp.demo.model.walkRequest;

import com.theWalkingDogsApp.demo.model.schedule.WeekDay;
import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class WeekDayWalk {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private WeekDay weekDay;
    @ElementCollection
    @Column(name = "walking_hour")
    @CollectionTable(joinColumns = @JoinColumn(name = "week_day_walk_id"))
    private List<LocalTime> walkingHours;

    public WeekDayWalk(WeekDay weekDay, List<LocalTime> walkingHours) {
        this.weekDay = weekDay;
        this.walkingHours = walkingHours;
    }
}
