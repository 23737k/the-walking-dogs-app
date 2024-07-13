package com.theWalkingDogsApp.demo.repository;

import com.theWalkingDogsApp.demo.model.walkRequest.WalkRequest;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WalkRequestRepo extends JpaRepository<WalkRequest, Integer> {
  void deleteAllByDogWalkerId(int dogWalkerId);
  List<WalkRequest> findAllByDogWalkerId(int dogWalkerId);
}
