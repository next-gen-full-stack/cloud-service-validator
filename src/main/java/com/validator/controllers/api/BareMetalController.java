package com.validator.controllers.api;

import com.validator.beans.BareMetalValidationResult;
import com.validator.beans.configuration.BareMetalConfiguration;
import java.time.Duration;
import java.time.LocalDateTime;
import javax.servlet.http.HttpServletRequest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@ComponentScan("com.validator.beans")
public class BareMetalController {

  private BareMetalValidationResult barMetalValidationResult;
  private BareMetalConfiguration barMetalConfiguration;

  public BareMetalController(
      BareMetalValidationResult serviceValidationResult,
      BareMetalConfiguration serviceConfiguration) {
    this.barMetalValidationResult = serviceValidationResult;
    this.barMetalConfiguration = serviceConfiguration;
  }

  @RequestMapping("/api/v1/ping/aliyun/barMetal")
  BareMetalValidationResult barMetal(HttpServletRequest request) {
    RestTemplate restTemplate = new RestTemplate();
    try {
      LocalDateTime oldDate = LocalDateTime.now();
      System.out.println("apiUrl=" + this.barMetalConfiguration.getApiUrl());
      this.barMetalValidationResult =
          restTemplate.getForObject(
              this.barMetalConfiguration.getApiUrl(), BareMetalValidationResult.class, 200);
      Duration duration = Duration.between(oldDate, LocalDateTime.now());
      this.barMetalValidationResult.setResponseTime(duration.toMillis());
    } catch (Exception e) {
      e.printStackTrace();
      System.out.println("" + e.getMessage());
    }
    return barMetalValidationResult;
  }
}
