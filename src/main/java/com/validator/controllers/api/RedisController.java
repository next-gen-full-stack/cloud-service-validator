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
  private final RedisAliValidationResult aliRedisValidationResult;

  public RedisController(RedisValidationResult serviceValidationResult,
      RedisAliValidationResult aliRedisValidationResult) {
    this.redisValidationResult = serviceValidationResult;
    this.aliRedisValidationResult = aliRedisValidationResult;
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
      this.redisValidationResult.setAccessibility(true);
    } catch (Exception e) {
      this.redisValidationResult.setAccessibility(false);
      System.err.println(e.getClass().getName() + ":[EXCEPtION] " + e.getMessage());
    }

    return this.redisValidationResult;
  }

  @RequestMapping("/api/v1/ping/ali/redis")
  RedisAliValidationResult aliRedis(HttpServletRequest request) {

    try {
      LocalDateTime oldDate = LocalDateTime.now();
      String result = this.redisValidationResult.ping();
      LocalDateTime newDate = LocalDateTime.now();
      // count seconds between dates
      Duration duration = Duration.between(oldDate, newDate);

      System.out.println(duration.getSeconds() + " seconds");
      this.aliRedisValidationResult.setResult(result);
      this.aliRedisValidationResult.setResponseTime(duration.toMillis());
      this.aliRedisValidationResult.setAccessibility(true);
    } catch (Exception e) {
      this.aliRedisValidationResult.setAccessibility(false);
      System.err.println(e.getClass().getName() + ":[EXCEPtION-Ali] " + e.getMessage());
    }

    return aliRedisValidationResult;
  }
}
