package com.theWalkingDogsApp.demo.repository;

import com.theWalkingDogsApp.demo.model.user.User;
import com.theWalkingDogsApp.demo.model.walkRequest.WalkRequest;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface WalkRequestRepo extends JpaRepository<WalkRequest, Integer> {
  void deleteAllByDogWalkerId(int dogWalkerId);
  List<WalkRequest> findAllByDogWalkerId(int dogWalkerId);
  @Query("SELECT w FROM WalkRequest w LEFT JOIN User u ON u.dogWalker.id = w.dogWalker.id")
  List<WalkRequest> findAllByUser(User user);
}
