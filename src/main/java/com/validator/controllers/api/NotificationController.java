package com.validator.controllers.api;


import javax.servlet.http.HttpServletRequest;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.validator.beans.NotificationValidationResult;

@RestController
@ComponentScan("com.validator.beans")
public class NotificationController {
	private final NotificationValidationResult notificationValidationResult;
	
	public NotificationController(NotificationValidationResult serviceValidationResult) {
	    this.notificationValidationResult = serviceValidationResult;
	  }
	@RequestMapping("/api/v1/ping/azurePush")
	  NotificationValidationResult azureNotification(HttpServletRequest request) {
	    try {
	    	notificationValidationResult.setService("AzureNotification");
	    	notificationValidationResult.azurePing();
	    } catch (Exception e) {
	      System.err.println(e.getClass().getName() + ":[EXCEPtION] " + e.getMessage());
	    }
	    return notificationValidationResult;
	  }
	
	@RequestMapping("/api/v1/ping/aliPush")
	  NotificationValidationResult aliNotification(HttpServletRequest request) {
	    try {
	    	notificationValidationResult.setService("AliNotification");
	    	notificationValidationResult.aliPing();
	    	notificationValidationResult.setAccessibility(true);
	    	notificationValidationResult.setScalability(true);
	    } catch (Exception e) {
	      System.err.println(e.getClass().getName() + ":[EXCEPtION] " + e.getMessage());
	    }
	    return notificationValidationResult;
	  }
}
