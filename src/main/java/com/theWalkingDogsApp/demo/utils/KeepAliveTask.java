package com.theWalkingDogsApp.demo.utils;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@RequiredArgsConstructor
@Slf4j
public class KeepAliveTask {
  private final RestTemplate restTemplate;
  @Value("${backend.baseUrl}")
  private String BACKEND_URL;
  @Value("${backend.api.base-path}")
  private String API_BASE_PATH;

  @Scheduled(fixedRate = 840000)
  public void keepAlive() {
    String endpoint = BACKEND_URL + API_BASE_PATH + "/keepAlive";
    restTemplate.getForObject(endpoint, String.class);
    log.info("Keeping server alive");
  }
}
