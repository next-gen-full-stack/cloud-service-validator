package com.validator.controllers.api;

import com.validator.beans.NotificationValidationResult;
import com.validator.beans.configuration.AliNotiConfiguration;
import com.validator.beans.configuration.AzNotiConfiguration;
import java.time.Duration;
import java.time.LocalDateTime;
import javax.servlet.http.HttpServletRequest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@ComponentScan("com.validator.beans")
public class NotificationController {
  private NotificationValidationResult notificationValidationResult;
  private AzNotiConfiguration azConfig;
  private AliNotiConfiguration aliConfig;

  public NotificationController(
      NotificationValidationResult serviceValidationResult,
      AzNotiConfiguration azNotiConfiguration,
      AliNotiConfiguration aliNotiConfiguration) {
    this.notificationValidationResult = serviceValidationResult;
    this.azConfig = azNotiConfiguration;
    this.aliConfig = aliNotiConfiguration;
  }

  @RequestMapping("/api/v1/ping/azurePush")
  NotificationValidationResult azureNotification(HttpServletRequest request) {
    try {
      // notificationValidationResult.setService("AzureNotification");
      LocalDateTime oldDate = LocalDateTime.now();
      boolean bool = notificationValidationResult.azurePing(azConfig);
      LocalDateTime newDate = LocalDateTime.now();
      Duration duration = Duration.between(oldDate, newDate);
      if (bool) {
        notificationValidationResult.setAccessibility(true);
        notificationValidationResult.setScalability(true);
      } else {
        notificationValidationResult.setAccessibility(false);
      }
      this.notificationValidationResult.setResponseTime(duration.toMillis());
    } catch (Exception e) {
      notificationValidationResult.setAccessibility(false);
      System.err.println(e.getClass().getName() + ":[EXCEPtION] " + e.getMessage());
    }
    return notificationValidationResult;
  }

  @RequestMapping("/api/v1/ping/aliPush")
  NotificationValidationResult aliNotification(HttpServletRequest request) {
    try {
      // notificationValidationResult.setService("AliNotification");
      LocalDateTime oldDate = LocalDateTime.now();
      boolean bool = notificationValidationResult.aliPing(aliConfig);
      LocalDateTime newDate = LocalDateTime.now();
      Duration duration = Duration.between(oldDate, newDate);
      if (bool) {
        notificationValidationResult.setAccessibility(true);
        notificationValidationResult.setScalability(true);
      } else {
        notificationValidationResult.setAccessibility(false);
      }
      this.notificationValidationResult.setResponseTime(duration.toMillis());
    } catch (Exception e) {
      System.err.println(e.getClass().getName() + ":[EXCEPtION] " + e.getMessage());
      notificationValidationResult.setAccessibility(false);
    }
    return notificationValidationResult;
  }
}
