package com.theWalkingDogsApp.demo.repository;

import com.theWalkingDogsApp.demo.model.dogOwner.DogOwner;
import com.theWalkingDogsApp.demo.model.dogWalker.DogWalker;
import com.theWalkingDogsApp.demo.model.walkRequest.WalkRequest;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface WalkRequestRepo extends JpaRepository<WalkRequest, Integer> {

//  @Query("SELECT w FROM WalkRequest w LEFT JOIN User u ON u.dogWalker.id = w.dogWalker.id")
  List<WalkRequest> findAllByDogWalker(DogWalker dogWalker);
  List<WalkRequest> findAllByDogOwner(DogOwner dogOwner);

  void deleteAllByDogWalkerId(Integer id);
  void deleteAllByDogOwnerId(Integer id);

}
