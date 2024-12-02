package com.theWalkingDogsApp.demo.dto.response.walkBooking;

import com.theWalkingDogsApp.demo.dto.response.walkRequest.PetRes;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class WalkBookingRes {
    private Integer id;
    private List<PetRes> pets;
    private String phoneNumber;
    private String message;
    private List<WalkRes> walks;
    private Integer dogWalkerId;
    private Integer dogOwnerId;
}
