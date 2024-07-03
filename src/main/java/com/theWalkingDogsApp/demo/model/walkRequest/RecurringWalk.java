package com.theWalkingDogsApp.demo.model.walkRequest;

import com.theWalkingDogsApp.demo.model.DogWalker;
import com.theWalkingDogsApp.demo.model.schedule.WeekDay;
import com.theWalkingDogsApp.demo.model.walkBooking.Walk;
import com.theWalkingDogsApp.demo.model.walkBooking.WalkBooking;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.cglib.core.Local;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
public class RecurringWalk extends WalkRequest{
    private List<WeekDayWalk> weekDayWalks;
    private LocalDate startOfService;
    private LocalDate endOfService;

    private RecurringWalk(List<Pet> pets, String phoneNumber, String message, DogWalker dogWalker,
                          List<WeekDayWalk> weekDayWalks, LocalDate startOfService, LocalDate endOfService){
        super(pets,phoneNumber,message,dogWalker);
        this.weekDayWalks = weekDayWalks;
        this.startOfService = startOfService;
        this.endOfService = endOfService;
    }

    @Override
    public WalkBooking createBooking() {
        //List<Walk> walks = this.weekDayWalks.stream().map(w->new Walk())

        //Determinar fecha inicio (date)
        //Determinar fecha fin (date)
        //obtener una lista de dates entre esos dos
        //filtrado previo de dias de semana correctos
        //recorrer this.weekDaysWalk y por cada uno:
        //  de los dias, filtrar aquellos que tengan como weekday a ..
        //  crear por cada walking hours un walk


        return new WalkBooking(this.pets, this.phoneNumber, this.message, this.dogWalker, null/*walks*/);
    }

    private List<Walk> getWalks(){
        List<Walk> walks = new ArrayList<>();
        List<WeekDay> weekDays = this.weekDayWalks.stream().map(WeekDayWalk::getWeekDay).toList();
        List<LocalDate> serviceDays = this.startOfService.datesUntil(this.endOfService.plusDays(1))
                .filter(d-> weekDays.contains(WeekDay.valueOf(d.getDayOfWeek().toString()))).toList();

        for(WeekDayWalk weekDayWalk :  this.weekDayWalks){

        }

        return null;
    }


}
