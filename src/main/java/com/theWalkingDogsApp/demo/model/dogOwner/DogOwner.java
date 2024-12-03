package com.theWalkingDogsApp.demo.model.dogOwner;

import com.theWalkingDogsApp.demo.model.dogWalker.DogWalker;
import com.theWalkingDogsApp.demo.model.pet.Pet;
import com.theWalkingDogsApp.demo.model.walkRequest.WalkRequest;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DogOwner {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;
  @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
  private List<WalkRequest> walkRequests = new ArrayList<>();
  @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "dogOwner")
  private List<Pet> pets = new ArrayList<>();
}
