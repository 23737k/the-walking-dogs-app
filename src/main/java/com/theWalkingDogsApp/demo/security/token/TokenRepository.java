package com.theWalkingDogsApp.demo.security.token;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TokenRepository extends JpaRepository<Token, Integer>{
  Optional<Token> findByToken(String token);

}
