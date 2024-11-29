package com.theWalkingDogsApp.demo.repository;

import com.theWalkingDogsApp.demo.model.dogWalker.DogWalker;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface DogWalkerRepo extends JpaRepository<DogWalker,Integer>,
    JpaSpecificationExecutor<DogWalker> {
}
