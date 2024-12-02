package com.theWalkingDogsApp.demo.service;

import ch.qos.logback.classic.encoder.JsonEncoder;
import com.theWalkingDogsApp.demo.model.dogOwner.DogOwner;
import com.theWalkingDogsApp.demo.model.dogWalker.DogWalker;
import com.theWalkingDogsApp.demo.model.user.Role;
import com.theWalkingDogsApp.demo.model.user.User;
import com.theWalkingDogsApp.demo.model.user.UserProfile;
import com.theWalkingDogsApp.demo.repository.UserRepository;
import com.theWalkingDogsApp.demo.security.auth.RegisterReq;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserService {
  private final UserRepository userRepository;
  private final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

  public User findUserByEmail(String email) {
    return  userRepository.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException("User with email " + email + " not found"));
  }

  public boolean userExists(String email) {
    return userRepository.findByEmail(email).isPresent();
  }

  @Transactional
  public User save(User user) {
    return userRepository.save(user);
  }

  @Transactional
  public User createUser(RegisterReq req){
    UserProfile userProfile = UserProfile.builder()
            .firstname(req.getFirstname())
            .lastname(req.getLastname())
            .dob(req.getDob())
            .phoneNumber(req.getPhoneNumber())
            .build();

    User user = User.builder()
            .dogOwner(new DogOwner())
            .dogWalker(new DogWalker())
            .email(req.getEmail())
            .password(passwordEncoder.encode(req.getPassword()))
            .role(Role.USER)
            .profile(userProfile)
            .build();
    return userRepository.save(user);
  }
}
