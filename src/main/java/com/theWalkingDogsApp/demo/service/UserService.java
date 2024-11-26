package com.theWalkingDogsApp.demo.service;

import com.theWalkingDogsApp.demo.model.user.User;
import com.theWalkingDogsApp.demo.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
  private final UserRepository userRepository;

  public User findUserByEmail(String email) {
    return  userRepository.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException("User with email " + email + " not found"));
  }

  public boolean userExists(String email) {
    return userRepository.findByEmail(email).isPresent();
  }

  public User save(User user) {
    return userRepository.save(user);
  }
}
