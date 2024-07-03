package com.theWalkingDogsApp.demo.repository;

import com.theWalkingDogsApp.demo.careGiver.CareGiver;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CareGiverRepo extends JpaRepository<CareGiver, Integer> {
}
