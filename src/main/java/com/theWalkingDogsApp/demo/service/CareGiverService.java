package com.theWalkingDogsApp.demo.service;

import com.theWalkingDogsApp.demo.dto.request.careGiver.DogWalkerReqDto;
import com.theWalkingDogsApp.demo.dto.response.careGiver.CareGiverResDto;
import com.theWalkingDogsApp.demo.dto.request.careGiver.CareGiverReqDto;
import com.theWalkingDogsApp.demo.dto.response.careGiver.DogWalkerResDto;
import com.theWalkingDogsApp.demo.filter.CareGiverFilter;
import com.theWalkingDogsApp.demo.model.careGiver.DogWalker;
import com.theWalkingDogsApp.demo.model.careGiver.CareGiver;
import com.theWalkingDogsApp.demo.repository.CareGiverCustom;
import com.theWalkingDogsApp.demo.repository.CareGiverRepo;
import com.theWalkingDogsApp.demo.service.mapper.careGiver.CareGiverMapper;
import com.theWalkingDogsApp.demo.service.mapper.careGiver.DogWalkerMapper;
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
  private final CareGiverCustom careGiverCustom;

  public List<CareGiverResDto> getCareGivers(CareGiverFilter filter){
    return careGiverCustom.findCareGivers(filter).stream().map(careGiverMapper::toCareGiverResponseDto).toList();
  }

  public CareGiverResDto getCareGiverById(Integer id){
    return careGiverRepo.findById(id).map(careGiverMapper::toCareGiverResponseDto).orElse(null);
  }

  public CareGiverResDto addCareGiver(CareGiverReqDto careGiverReqDto){
    CareGiver careGiver = careGiverRepo.save(careGiverMapper.toCareGiver(careGiverReqDto));
    return careGiverMapper.toCareGiverResponseDto(careGiver);
  }

  public List<DogWalkerResDto> getAllDogWalkerServices(){
    return careGiverRepo.findAll().stream().map(CareGiver::getDogWalker).map(dogWalkerMapper::toDogWalkerResponseDto).toList();
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

  public void setDogWalkerService(Integer id, DogWalkerReqDto dogWalkerReqDto){
    CareGiver careGiver = validate(id);
    DogWalker updatedDogWalker = dogWalkerMapper.toDogWalker(dogWalkerReqDto);
    updatedDogWalker.setActive(true);
    updatedDogWalker.setId(careGiver.getDogWalker().getId());
    careGiver.setDogWalker(updatedDogWalker);
    careGiverRepo.save(careGiver);
  }

  public DogWalkerResDto getDogWalker(Integer id){
    CareGiver careGiver = validate(id);
    return dogWalkerMapper.toDogWalkerResponseDto(careGiver.getDogWalker());
  }

  public CareGiver validate(Integer id){
    return careGiverRepo.findById(id).orElseThrow(()->new EntityNotFoundException("CareGiver not found"));
  }


}
