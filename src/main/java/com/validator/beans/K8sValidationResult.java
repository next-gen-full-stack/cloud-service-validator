package com.validator.beans;

import com.google.auto.value.AutoValue;
import com.validator.beans.base.ServiceValidationResult;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@AutoValue
@ConfigurationProperties("k8s")
public class K8sValidationResult extends ServiceValidationResult {

  private Long responseTime;

  public Long getResponseTime() {
    return responseTime;
  }

  public void setResponseTime(long responseTime) {
    this.responseTime = responseTime;
  }
}
