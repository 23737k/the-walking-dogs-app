package com.theWalkingDogsApp.demo.dto.request.walk;

import com.theWalkingDogsApp.demo.model.walkBooking.WalkStatus;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.time.LocalTime;

public record WalkReq(
        @Schema(example = "2025-07-02")
        @Future(message = "must be a future date")
        @NotNull(message = "date must not be null")
        LocalDate date,
        @Schema(example = "21:30:00")
        @NotNull(message = "time must not be null")
        LocalTime time,
        @Schema(example = "COMPLETED")
        @NotNull(message = "status must not be null")
        WalkStatus status
) {
}
