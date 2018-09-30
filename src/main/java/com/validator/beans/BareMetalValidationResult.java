package com.validator.beans;

import com.google.auto.value.AutoValue;
import com.validator.beans.base.ServiceValidationResult;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@AutoValue
@ConfigurationProperties("bare.metal")
public class BareMetalValidationResult extends ServiceValidationResult {

  private Long responseTime;

  public Long getResponseTime() {
    return responseTime;
  }

  public void setResponseTime(Long responseTime) {
    this.responseTime = responseTime;
  }
}
