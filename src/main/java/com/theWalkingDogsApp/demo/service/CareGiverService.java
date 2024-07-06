package com.theWalkingDogsApp.demo.service;

import com.theWalkingDogsApp.demo.dto.request.DogWalkerRequestDto;
import com.theWalkingDogsApp.demo.dto.response.CareGiverResponseDto;
import com.theWalkingDogsApp.demo.dto.request.CareGiverRequestDto;
import com.theWalkingDogsApp.demo.model.DogWalker;
import com.theWalkingDogsApp.demo.model.careGiver.CareGiver;
import com.theWalkingDogsApp.demo.repository.CareGiverRepo;
import com.theWalkingDogsApp.demo.service.mapper.CareGiverMapper;
import com.theWalkingDogsApp.demo.service.mapper.DogWalkerMapper;
import jakarta.persistence.EntityNotFoundException;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CareGiverService {
  private final CareGiverRepo careGiverRepo;
  private final CareGiverMapper careGiverMapper;
  private final DogWalkerMapper dogWalkerMapper;

  public List<CareGiverResponseDto> getCareGivers(){
    return careGiverRepo.findAll().stream().map(careGiverMapper::toCareGiverResponseDto).toList();
  }

  public CareGiverResponseDto addCareGiver(CareGiverRequestDto careGiverRequestDto){
    CareGiver careGiver = careGiverRepo.save(careGiverMapper.toCareGiver(careGiverRequestDto));
    return careGiverMapper.toCareGiverResponseDto(careGiver);
  }

  public void deleteCareGiver(Integer id){
    if(careGiverRepo.existsById(id))
      careGiverRepo.deleteById(id);
    else
      throw new EntityNotFoundException("CareGiver not found");
  }

  public void deleteAllCareGivers(){
    careGiverRepo.deleteAll();
  }

  public void setDogWalkerService(Integer id, DogWalkerRequestDto dogWalkerRequestDto){
    CareGiver careGiver = careGiverRepo.findById(id).orElseThrow(()->new EntityNotFoundException("CareGiver not found"));
    DogWalker updatedDogWalker = dogWalkerMapper.toDogWalker(dogWalkerRequestDto);
    updatedDogWalker.setActive(true);
    updatedDogWalker.setId(careGiver.getDogWalker().getId());
    careGiver.setDogWalker(updatedDogWalker);
    careGiverRepo.save(careGiver);
  }

  public DogWalker getDogWalker(Integer id){
    CareGiver careGiver = careGiverRepo.findById(id).orElseThrow(()->new EntityNotFoundException("CareGiver not found"));
    return careGiver.getDogWalker();
  }

}
