package com.theWalkingDogsApp.demo.model.walkBooking;

import com.theWalkingDogsApp.demo.model.walkRequest.Pet;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@Entity
public class WalkBooking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @ManyToMany
    private List<Pet> pets;
    private String phoneNumber;
    private String message;
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "walk_booking_id")
    private List<Walk> walks;

    public WalkBooking(List<Pet> pets, String phoneNumber, String message, List<Walk> walks) {
        this.pets = pets;
        this.phoneNumber = phoneNumber;
        this.message = message;
        this.walks = walks;
    }
}
