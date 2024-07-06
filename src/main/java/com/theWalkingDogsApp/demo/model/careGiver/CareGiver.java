package com.theWalkingDogsApp.demo.model.careGiver;

import com.theWalkingDogsApp.demo.model.DogWalker;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@Builder
public class CareGiver {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;
  @OneToOne(cascade = CascadeType.ALL)
  private BasicInfo basicInfo;
  private String bio;
 @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
  private DogWalker dogWalker;

    public CareGiver(BasicInfo basicInfo, String bio, DogWalker dogWalker) {
        this.basicInfo = basicInfo;
        this.bio = bio;
        this.dogWalker = dogWalker;
    }
    public CareGiver(){
      this.dogWalker = new DogWalker();
    }
}
