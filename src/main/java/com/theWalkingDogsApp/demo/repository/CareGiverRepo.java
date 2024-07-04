package com.theWalkingDogsApp.demo.repository;

import com.theWalkingDogsApp.demo.careGiver.CareGiver;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CareGiverRepo extends JpaRepository<CareGiver, Integer> {
}
