package com.theWalkingDogsApp.demo.dto.response.walkRequest;

import lombok.Data;

import java.util.List;

@Data
public class WalkRequestRes {
  protected Integer id;
  protected List<PetRes> pets;
  protected String phoneNumber;
  protected String message;
  protected Integer dogWalkerId;
  protected Integer dogOwnerId;
  }
