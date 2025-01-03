package com.theWalkingDogsApp.demo.model.walkRequest;

import static com.theWalkingDogsApp.demo.model.walkBooking.WalkStatus.SCHEDULED;

import com.theWalkingDogsApp.demo.model.dogOwner.DogOwner;
import com.theWalkingDogsApp.demo.model.dogWalker.DogWalker;
import com.theWalkingDogsApp.demo.model.pet.Pet;
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
  private List<WalkPerDate> walksPerDate;

  public OneTimeWalk(List<Pet> pets, String phoneNumber, String message, DogWalker dogWalker, DogOwner dogOwner, List<WalkPerDate> walksPerDate) {
    super(pets, phoneNumber, message, dogWalker,dogOwner);
    this.walksPerDate = walksPerDate;
  }

  @Override
  public WalkBooking createBooking() {
    return new WalkBooking(this.pets,this.phoneNumber,this.message,getWalks(), this.getDogOwner(), this.getDogWalker());
  }

  private List<Walk> getWalks(){
    List<Walk> walks = new ArrayList<>();
    for(WalkPerDate walkPerDate : walksPerDate){
      for(LocalTime time : walkPerDate.getWalkingHours()){
        walks.add(new Walk(walkPerDate.getDate(),time, SCHEDULED));
      }
    }
    return walks;
  }

}
