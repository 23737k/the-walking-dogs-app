package com.theWalkingDogsApp.demo.repository;

import com.theWalkingDogsApp.demo.model.DogWalker;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DogWalkerRepo extends JpaRepository<DogWalker,Integer> {
}
