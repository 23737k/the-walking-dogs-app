package com.theWalkingDogsApp.demo.model.walkRequest;

import static com.theWalkingDogsApp.demo.model.walkBooking.WalkStatus.SCHEDULED;

import com.theWalkingDogsApp.demo.model.dogOwner.DogOwner;
import com.theWalkingDogsApp.demo.model.dogWalker.DogWalker;
import com.theWalkingDogsApp.demo.model.pet.Pet;
import com.theWalkingDogsApp.demo.model.schedule.WeekDay;
import com.theWalkingDogsApp.demo.model.walkBooking.Walk;
import com.theWalkingDogsApp.demo.model.walkBooking.WalkBooking;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@Entity
public class RecurringWalk extends WalkRequest{
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "recurring_walk_id")
    private List<WalkPerWeek> walksPerWeekDays;
    private LocalDate startOfService;
    private LocalDate endOfService;

    public RecurringWalk(List<Pet> pets, String phoneNumber, String message, DogWalker dogWalker,
                         DogOwner dogOwner,
                         List<WalkPerWeek> walksPerWeekDays, LocalDate startOfService, LocalDate endOfService){
        super(pets,phoneNumber,message,dogWalker,dogOwner);
        this.walksPerWeekDays = walksPerWeekDays;
        this.startOfService = startOfService;
        this.endOfService = endOfService;
    }

    @Override
    public WalkBooking createBooking() {
        return new WalkBooking(this.pets, this.phoneNumber, this.message, getWalks(), this.getDogOwner(),this.getDogWalker());
    }

    private List<Walk> getWalks(){
        List<Walk> walks = new ArrayList<>();
        List<WeekDay> weekDays = this.walksPerWeekDays.stream().map(WalkPerWeek::getWeekDay).toList();
        List<LocalDate> serviceDays = this.startOfService.datesUntil(this.endOfService.plusDays(1))
                .filter(d-> weekDays.contains(WeekDay.valueOf(d.getDayOfWeek().toString()))).toList();

        for(WalkPerWeek walksPerWeekDay :  this.walksPerWeekDays){
            WeekDay weekDay = walksPerWeekDay.getWeekDay();
            List<LocalDate> sameWeekDayDates = serviceDays.stream().filter(d -> hasSameWeekDay(d,weekDay)).toList();
            for(LocalDate sameWeekDayDate : sameWeekDayDates){
                for (LocalTime walkHour : walksPerWeekDay.getWalkingHours()){
                    walks.add(new Walk(sameWeekDayDate,walkHour,SCHEDULED));
                }
            }
        }
        return walks;
    }


    private boolean hasSameWeekDay(LocalDate date, WeekDay weekDay){
        return WeekDay.valueOf(date.getDayOfWeek().toString()).equals(weekDay);
    }



}
