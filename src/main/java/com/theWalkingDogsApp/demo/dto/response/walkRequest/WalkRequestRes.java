package com.theWalkingDogsApp.demo.dto.response.walkRequest;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;

@Data
@Schema(oneOf = { OneTimeWalkRes.class, RecurringWalkRes.class })
public class WalkRequestRes {
  protected Integer id;
  protected List<PetRes> pets;
  protected String phoneNumber;
  protected String message;
  protected Integer dogWalkerId;
  protected Integer dogOwnerId;
  }
