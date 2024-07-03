package com.theWalkingDogsApp.demo.model.walkRequest;

import static com.theWalkingDogsApp.demo.model.walkBooking.WalkStatus.SCHEDULED;

import com.theWalkingDogsApp.demo.model.DogWalker;
import com.theWalkingDogsApp.demo.model.schedule.WeekDay;
import com.theWalkingDogsApp.demo.model.walkBooking.Walk;
import com.theWalkingDogsApp.demo.model.walkBooking.WalkBooking;
import java.time.LocalTime;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
public class RecurringWalk extends WalkRequest{
    private List<WeekDayWalk> weekDayWalks;
    private LocalDate startOfService;
    private LocalDate endOfService;

    public RecurringWalk(List<Pet> pets, String phoneNumber, String message, DogWalker dogWalker,
                          List<WeekDayWalk> weekDayWalks, LocalDate startOfService, LocalDate endOfService){
        super(pets,phoneNumber,message,dogWalker);
        this.weekDayWalks = weekDayWalks;
        this.startOfService = startOfService;
        this.endOfService = endOfService;
    }

    @Override
    public WalkBooking createBooking() {
        return new WalkBooking(this.pets, this.phoneNumber, this.message, this.dogWalker, getWalks());
    }

    private List<Walk> getWalks(){
        List<Walk> walks = new ArrayList<>();
        List<WeekDay> weekDays = this.weekDayWalks.stream().map(WeekDayWalk::getWeekDay).toList();
        List<LocalDate> serviceDays = this.startOfService.datesUntil(this.endOfService.plusDays(1))
                .filter(d-> weekDays.contains(WeekDay.valueOf(d.getDayOfWeek().toString()))).toList();

        for(WeekDayWalk weekDayWalk :  this.weekDayWalks){
            WeekDay weekDay = weekDayWalk.getWeekDay();
            List<LocalDate> sameWeekDayDates = serviceDays.stream().filter(d -> hasSameWeekDay(d,weekDay)).toList();
            for(LocalDate sameWeekDayDate : sameWeekDayDates){
                for (LocalTime walkHour : weekDayWalk.getWalkingHours()){
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
