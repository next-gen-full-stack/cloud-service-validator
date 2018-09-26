package com.validator.controllers.api;

import com.validator.beans.*;
import javax.servlet.http.HttpServletRequest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@ComponentScan("com.validator.beans")
public class K8sController {

  private K8sValidationResult k8sValidationResult;
  private RestTemplate restTemplate;

  public K8sController(K8sValidationResult k8sValidationResult) {
    this.restTemplate = new RestTemplate();
    this.k8sValidationResult = k8sValidationResult;
  }

  /**
   * This is in the environment that we're going to access and validate
   *
   * @param request
   * @return
   */
  @RequestMapping("/api/v1/ping/azure/k8s")
  K8sValidationResult k8s(HttpServletRequest request) {

    // LocalDateTime oldDate = LocalDateTime.now();

    // this.k8sValidationResult =
    //     this.restTemplate.getForObject(
    //         "http://42.159.89.106:8888/api/v1/ping/k8s", K8sValidationResult.class);
    // Duration duration = Duration.between(oldDate, LocalDateTime.now());

    // this.k8sValidationResult.setResponseTime(duration.toMillis());

    return this.k8sValidationResult;
  }
}
