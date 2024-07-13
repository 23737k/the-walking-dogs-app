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
public class OneTimeWalk extends WalkRequest{
  @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
  @JoinColumn(name = "one_time_request_id")
  private List<WalksPerDate> walksPerDates;

  public OneTimeWalk(List<Pet> pets, String phoneNumber, String message, DogWalker dogWalker, List<WalksPerDate> walksPerDates) {
    super(pets, phoneNumber, message, dogWalker);
    this.walksPerDates = walksPerDates;
  }

  @Override
  public WalkBooking createBooking() {
    return new WalkBooking(this.pets,this.phoneNumber,this.message, this.dogWalker,getWalks());
  }

  private List<Walk> getWalks(){
    List<Walk> walks = new ArrayList<>();
    for(WalksPerDate walksPerDate : walksPerDates){
      for(LocalTime time : walksPerDate.getWalkingHours()){
        walks.add(new Walk(walksPerDate.getDate(),time, SCHEDULED));
      }
    }
    return walks;
  }

}
