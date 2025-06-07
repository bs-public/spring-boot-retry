package com.app;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RetryController {
  private final ExternalApiService service;

  public RetryController(ExternalApiService service) {
    this.service = service;
  }

  @GetMapping("/test-retry")
  public String testRetry() {
    return service.callUnstableService();
  }
}
