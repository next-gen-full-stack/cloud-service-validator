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
public class RedisController {

  private final RedisValidationResult redisValidationResult;

  public RedisController(RedisValidationResult serviceValidationResult) {
    this.redisValidationResult = serviceValidationResult;
  }

  @RequestMapping("/api/v1/ping/redis")
  RedisValidationResult redis(HttpServletRequest request) {

    try {
      LocalDateTime oldDate = LocalDateTime.now();
      String result = this.redisValidationResult.ping();
      LocalDateTime newDate = LocalDateTime.now();
      // count seconds between dates
      Duration duration = Duration.between(oldDate, newDate);

      System.out.println(duration.getSeconds() + " seconds");
      this.redisValidationResult.setResult(result);
      this.redisValidationResult.setResponseTime(duration.toMillis());

    } catch (Exception e) {
      System.err.println(e.getClass().getName() + ":[EXCEPtION] " + e.getMessage());
    }

    return this.redisValidationResult;
  }
}
