package com.theWalkingDogsApp.demo.model.walkRequest;

import static com.theWalkingDogsApp.demo.model.walkBooking.WalkStatus.SCHEDULED;

import com.theWalkingDogsApp.demo.model.careGiver.DogWalker;
import com.theWalkingDogsApp.demo.model.walkBooking.Walk;
import com.theWalkingDogsApp.demo.model.walkBooking.WalkBooking;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
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
public class OneTimeWalkReq extends WalkRequest{
  @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
  @JoinColumn(name = "one_time_request_id")
  private List<SingleDayWalk> singleDayWalks;

  public OneTimeWalkReq(List<Pet> pets, String phoneNumber, String message, DogWalker dogWalker, List<SingleDayWalk> singleDayWalks) {
    super(pets, phoneNumber, message, dogWalker);
    this.singleDayWalks = singleDayWalks;
  }

  @Override
  public WalkBooking createBooking() {
    return new WalkBooking(this.pets,this.phoneNumber,this.message, this.dogWalker,getWalks());
  }

  private List<Walk> getWalks(){
    List<Walk> walks = new ArrayList<>();
    for(SingleDayWalk singleDayWalk : singleDayWalks){
      for(LocalTime time : singleDayWalk.getWalkingHours()){
        walks.add(new Walk(singleDayWalk.getDate(),time, SCHEDULED));
      }
    }
    return walks;
  }

}
