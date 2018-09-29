package com.validator.controllers.api;

import com.validator.beans.*;
import java.time.Duration;
import java.time.LocalDateTime;
import javax.servlet.http.HttpServletRequest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@ComponentScan("com.validator.beans")
public class KafkaController {
  private MQValidationResult mqValidationResult;

  public KafkaController(MQValidationResult mqValidationResult) {
    this.mqValidationResult = mqValidationResult;
  }

  /**
   * Only support internal communication in the same region
   *
   * @param request
   * @return
   */
  @RequestMapping("/api/v1/ping/ali/kafka")
  MQValidationResult kafkaAli(HttpServletRequest request) {
    LocalDateTime oldDate = LocalDateTime.now();
    Duration duration = Duration.between(oldDate, LocalDateTime.now());
    mqValidationResult.setResponseTime(duration.toMillis());
    mqValidationResult.setService("Kafka");
    mqValidationResult.setLocation("Ali");
    mqValidationResult.setAccessibility(false);
    return this.mqValidationResult;
  }

  /**
   * Only support internal communication in the internal network
   *
   * @param request
   * @return
   */
  @RequestMapping("/api/v1/ping/azure/kafka")
  MQValidationResult kafkaAzure(HttpServletRequest request) {
    LocalDateTime oldDate = LocalDateTime.now();
    Duration duration = Duration.between(oldDate, LocalDateTime.now());
    mqValidationResult.setResponseTime(duration.toMillis());
    mqValidationResult.setService("Kafka");
    mqValidationResult.setLocation("Azure");
    mqValidationResult.setAccessibility(false);
    return this.mqValidationResult;
  }
}
