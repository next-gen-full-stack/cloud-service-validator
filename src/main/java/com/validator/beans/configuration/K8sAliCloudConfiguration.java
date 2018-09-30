package com.validator.beans.configuration;

import com.google.auto.value.AutoValue;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@AutoValue
@ConfigurationProperties("k8s.alicloud")
public class K8sAliCloudConfiguration {

  private String apiUrl;

  public String getApiUrl() {
    return this.apiUrl;
  }

  public void setApiUrl(String apiUrl) {
    this.apiUrl = apiUrl;
  }
}
