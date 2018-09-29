package com.validator.controllers.api;

import com.validator.beans.*;
import com.validator.beans.configuration.K8sConfiguration;
import com.validator.beans.configuration.K8sConfigurationAliCloud;
import java.time.Duration;
import java.time.LocalDateTime;
import javax.servlet.http.HttpServletRequest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@ComponentScan("com.validator.beans")
public class K8sController {

  private K8sValidationResult k8sValidationResult;
  private K8sConfiguration k8sConfigurationAzure;
  private K8sConfigurationAliCloud k8sConfigurationAliCloud;
  private RestTemplate restTemplate;

  public K8sController(
      K8sValidationResult k8sValidationResult,
      K8sConfiguration k8sConfigurationAzure,
      K8sConfigurationAliCloud k8sConfigurationAliCloud) {
    this.restTemplate = new RestTemplate();
    this.k8sValidationResult = k8sValidationResult;
    this.k8sConfigurationAzure = k8sConfigurationAzure;
    this.k8sConfigurationAliCloud = k8sConfigurationAliCloud;
  }

  /**
   * This is in the environment that we're going to access and validate
   *
   * @param request
   * @return
   */
  @RequestMapping("/api/v1/ping/azure/k8s")
  K8sValidationResult azureK8s(HttpServletRequest request) {

    LocalDateTime oldDate = LocalDateTime.now();

    this.k8sValidationResult =
        this.restTemplate.getForObject(
            this.k8sConfigurationAzure.getApiUrl(), K8sValidationResult.class);
    Duration duration = Duration.between(oldDate, LocalDateTime.now());

    this.k8sValidationResult.setResponseTime(duration.toMillis());

    return this.k8sValidationResult;
  }

  /**
   * This is in the environment that we're going to access and validate
   *
   * @param request
   * @return
   */
  @RequestMapping("/api/v1/ping/alicloud/k8s")
  K8sValidationResult alicloudK8s(HttpServletRequest request) {

    LocalDateTime oldDate = LocalDateTime.now();

    this.k8sValidationResult =
        this.restTemplate.getForObject(
            this.k8sConfigurationAliCloud.getApiUrl(), K8sValidationResult.class);
    Duration duration = Duration.between(oldDate, LocalDateTime.now());

    this.k8sValidationResult.setResponseTime(duration.toMillis());

    return this.k8sValidationResult;
  }
}
