package com.theWalkingDogsApp.demo.repository;

import com.theWalkingDogsApp.demo.model.walkRequest.WalkRequest;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WalkRequestRepo extends JpaRepository<WalkRequest, Integer> {
}
