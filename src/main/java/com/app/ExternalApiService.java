package com.app;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Recover;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;

@Service
public class ExternalApiService {

  private static final Logger logger = LoggerFactory.getLogger(ExternalApiService.class);
  private final RestTemplate restTemplate = new RestTemplate();

  @Retryable(
      retryFor = {HttpServerErrorException.class},
      noRetryFor = {HttpClientErrorException.class},
      maxAttempts = 3,
      backoff = @Backoff(delay = 2000, multiplier = 1.5))
  public String callUnstableService() {
    logger.info("Trying to call API...");
    // This always returns 503 (Service Unavailable)
    return restTemplate.getForObject("https://httpbin.org/status/503", String.class);
  }

  @Recover
  public String recover(HttpServerErrorException e) {
    logger.error("All retries failed. Returning fallback response.", e);
    return "Fallback: External API is down";
  }
}
