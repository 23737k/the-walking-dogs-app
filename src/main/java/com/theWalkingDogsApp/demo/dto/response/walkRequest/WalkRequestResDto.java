package com.theWalkingDogsApp.demo.dto.response.walkRequest;

import java.util.List;
import lombok.Data;

@Data
public class WalkRequestResDto {
  protected Integer id;
  protected List<PetResDto> pets;
  protected String phoneNumber;
  protected String message;
  protected Integer dogWalkerId;
  }
