package com.theWalkingDogsApp.demo.dto.request.walkRequest;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@JsonTypeInfo(
    use = JsonTypeInfo.Id.NAME,
    property = "type"
)
@JsonSubTypes({
    @JsonSubTypes.Type(value = OneTimeReqDto.class, name = "one-time"),
    @JsonSubTypes.Type(value = RecurringWalkDto.class, name = "recurring")
})

@Data
@AllArgsConstructor
@NoArgsConstructor
public class WalkRequestDto {
  protected List<PetDto> pets;
  protected String phoneNumber;
  protected String message;
  protected Integer dogWalkerId;
}

