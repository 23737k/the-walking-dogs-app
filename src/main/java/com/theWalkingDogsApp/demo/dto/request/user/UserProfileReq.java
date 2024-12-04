package com.theWalkingDogsApp.demo.dto.request.user;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import java.time.LocalDate;

public record UserProfileReq(
        @Schema(example = "Jane")
        @NotBlank(message = "firstname must not be empty")
        String firstname,
        @Schema(example = "Williams")
        @NotBlank(message = "lastname must not be empty")
        String lastname,
        @Schema(example = "987654321")
        @NotBlank(message = "phoneNumber must not be empty")
        String phoneNumber,
        @Schema(example = "1997-07-23")
        @Past(message = "the day of birth must be a past date")
        @NotNull(message = "dob must not be empty")
        LocalDate dob
) {

}
