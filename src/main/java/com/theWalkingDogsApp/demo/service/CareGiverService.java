package com.theWalkingDogsApp.demo.service;

import com.theWalkingDogsApp.demo.dto.response.CareGiverResponseDto;
import com.theWalkingDogsApp.demo.dto.request.CareGiverRequestDto;
import com.theWalkingDogsApp.demo.model.careGiver.CareGiver;
import com.theWalkingDogsApp.demo.repository.CareGiverRepo;
import com.theWalkingDogsApp.demo.service.mapper.CareGiverMapper;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CareGiverService {
  private final CareGiverRepo careGiverRepo;
  private final CareGiverMapper careGiverMapper;

  public List<CareGiverResponseDto> getCareGivers(){
    return careGiverRepo.findAll().stream().map(careGiverMapper::toCareGiverResponseDto).toList();
  }

  public CareGiverResponseDto addCareGiver(CareGiverRequestDto careGiverRequestDto){
    CareGiver careGiver = careGiverRepo.save(careGiverMapper.toCareGiver(careGiverRequestDto));
    return careGiverMapper.toCareGiverResponseDto(careGiver);
  }

}
