package com.theWalkingDogsApp.demo.dto.request.walkRequest;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@JsonTypeInfo(
    use = JsonTypeInfo.Id.NAME,
    property = "type"
)
@JsonSubTypes({
    @JsonSubTypes.Type(value = OneTimeWalkReq.class, name = "one-time"),
    @JsonSubTypes.Type(value = RecurringWalkReq.class, name = "recurring")
})

@Data
@AllArgsConstructor
@NoArgsConstructor
public class WalkRequestReq {
  @Valid
  @NotEmpty(message = "pets field must not be empty")
  protected List<PetReqDto> pets;
  @NotBlank(message = "phoneNumber field must not be empty")
  protected String phoneNumber;
  @NotBlank(message = "message field must not be empty")
  protected String message;
  @NotNull(message = "dogWalkerId field must not be empty")
  protected Integer dogWalkerId;
}

