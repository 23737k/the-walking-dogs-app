package com.theWalkingDogsApp.demo.model.walkRequest;

import com.theWalkingDogsApp.demo.model.DogWalker;
import com.theWalkingDogsApp.demo.model.walkBooking.WalkBooking;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDate;
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
        return new WalkBooking(this.pets, this.phoneNumber, this.message, this.dogWalker, null/*walks*/);
    }
}
