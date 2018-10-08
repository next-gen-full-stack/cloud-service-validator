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
public class KafkaController {
  private MQValidationResult mqValidationResult;
  private @Autowired MQUsecase mqUsecase;

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
    boolean isOK =
        mqUsecase.testKafkaProducer(
            "172.31.207.53:9092,172.31.207.54:9092,172.31.207.55:9092", "test-kafka-jacky");
    mqValidationResult.setAccessibility(isOK);
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
    String brokers =
        "wn0-kafka.bn30bvr1eikujgaupttvmlz0gc.ax.internal.chinacloudapp.cn:9092,wn1-kafka.bn30bvr1eikujgaupttvmlz0gc.ax.internal.chinacloudapp.cn:9092";
    try {
    	mqValidationResult.setAccessibility(mqUsecase.testKafkaProducer(brokers, "test"));
    }catch(Exception e) {
    	mqValidationResult.setAccessibility(false);
    	e.printStackTrace();
    }
    return this.mqValidationResult;
  }
}
