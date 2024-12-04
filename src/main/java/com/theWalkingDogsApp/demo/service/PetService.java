package com.theWalkingDogsApp.demo.service;

import com.theWalkingDogsApp.demo.dto.request.walkRequest.PetReq;
import com.theWalkingDogsApp.demo.dto.response.walkRequest.PetRes;
import com.theWalkingDogsApp.demo.exceptions.ForbiddenAccessException;
import com.theWalkingDogsApp.demo.model.pet.Pet;
import com.theWalkingDogsApp.demo.model.user.User;
import com.theWalkingDogsApp.demo.repository.PetRepository;
import com.theWalkingDogsApp.demo.service.mapper.PetMapper;
import jakarta.persistence.EntityNotFoundException;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class PetService {
    private final PetRepository repo;
    private final PetMapper mapper;

    @Transactional
    public List<PetRes> getAllPets(User user) {
        List<Pet> pets =  repo.getPetsByDogOwner(user.getDogOwner());
        return pets.stream().map(mapper::toRes).toList();
    }
    @Transactional
    public PetRes getPetById(User user, Integer id){
        Pet pet = getById(user, id);
        return mapper.toRes(pet);
    }
    @Transactional
    public void deletePetById(User user, Integer id){
        Pet pet = getById(user, id);
        repo.deleteById(id);
    }

    @Transactional
    public Pet getById(User user, Integer id) {
        List<Pet> pets = repo.getPetsByDogOwner(user.getDogOwner());
        Pet pet = repo.findById(id).orElseThrow(()-> new EntityNotFoundException("Pet with id: " + id + " not found" ));
        if(!pets.contains(pet))
            throw new ForbiddenAccessException("You don't have permission to access this resource");
        return pet;
    }

    @Transactional
    public PetRes updatePet(User user, Integer id, PetReq req){
        Pet pet = getById(user, id);
        Pet updatedPet = mapper.toEntity(req);
        updatedPet.setDogOwner(user.getDogOwner());
        updatedPet.setId(pet.getId());
        return mapper.toRes(repo.save(updatedPet));
    }

    public PetRes createPet(User user, PetReq req){
        Pet newPet = mapper.toEntity(req);
        newPet.setDogOwner(user.getDogOwner());
        return mapper.toRes(repo.save(newPet));
    }

}
