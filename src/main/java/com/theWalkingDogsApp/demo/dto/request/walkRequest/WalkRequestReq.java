package com.theWalkingDogsApp.demo.dto.request.walkRequest;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


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
  @JsonProperty("type")
  private String type;
  @Valid
  @NotEmpty(message = "pets field must not be empty")
  protected List<PetReq> pets;
  @NotBlank(message = "phoneNumber field must not be empty")
  @Schema(example = "123456789")
  protected String phoneNumber;
  @NotBlank(message = "message field must not be empty")
  protected String message;
  @NotNull(message = "dogWalkerId field must not be empty")
  protected Integer dogWalkerId;
}

