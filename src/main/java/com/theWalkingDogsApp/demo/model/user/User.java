package com.theWalkingDogsApp.demo.model.user;

import com.theWalkingDogsApp.demo.model.dogWalker.DogWalker;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@Builder
@AllArgsConstructor
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String email;
    private String password;
    @OneToOne
    private UserProfile profile;
    @OneToOne
    private DogWalker dogWalker;
}
