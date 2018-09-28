package com.validator.beans.configuration;

import com.google.auto.value.AutoValue;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@AutoValue
@ConfigurationProperties("k8s.azure")
public class K8sConfiguration {

  private String apiUrl;

  public String getApiUrl() {
    return this.apiUrl;
  }

  public void setApiUrl(String apiUrl) {
    this.apiUrl = apiUrl;
  }
}
