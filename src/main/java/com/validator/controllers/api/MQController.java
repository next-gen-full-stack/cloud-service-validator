package com.validator.controllers.api;

import com.validator.application.usecase.MQUsecase;
import com.validator.beans.*;
import java.time.Duration;
import java.time.LocalDateTime;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@ComponentScan("com.validator.beans")
public class MQController {
  private MQValidationResult mqValidationResult;
  @Autowired MQUsecase mqUsecase;

  public MQController(MQValidationResult mqValidationResult) {
    this.mqValidationResult = mqValidationResult;
  }

  /**
   * This is in the environment that we're going to access and validate
   *
   * @param request
   * @return
   */
  @RequestMapping("/api/v1/ping/ali/mq")
  MQValidationResult kafka(HttpServletRequest request) {
    LocalDateTime oldDate = LocalDateTime.now();
    boolean isOk = mqUsecase.testMQProducer();
    mqValidationResult.setAccessibility(isOk);
    Duration duration = Duration.between(oldDate, LocalDateTime.now());
    mqValidationResult.setService("MQ");
    mqValidationResult.setResponseTime(duration.toMillis());
    mqValidationResult.setLocation("Ali");
    return this.mqValidationResult;
  }
}
