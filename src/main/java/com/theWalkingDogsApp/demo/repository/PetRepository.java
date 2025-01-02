package com.theWalkingDogsApp.demo.repository;

import com.theWalkingDogsApp.demo.model.dogOwner.DogOwner;
import com.theWalkingDogsApp.demo.model.pet.Pet;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PetRepository extends JpaRepository<Pet, Integer> {
    List<Pet> getPetsByDogOwner(DogOwner dogOwner);
}
