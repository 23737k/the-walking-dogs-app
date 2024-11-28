package com.theWalkingDogsApp.demo.dto.request.walkRequest;

import com.theWalkingDogsApp.demo.model.pet.Sex;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PetReq {
  @NotBlank(message = "name field must not be empty")
  private String name;
  @NotBlank(message = "breed field must not be empty")
  private String breed;
  @Positive(message = "weigh must me a positive number")
  @NotNull(message = "weighInKg field must not be null")
  private double weighInKg;
  @NotNull(message = "sex field must not be null")
  private Sex sex;
  @NotNull(message = "age field must not be null")
  @Positive(message = "age must me a positive number")
  private Integer age;
}
