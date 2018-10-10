package com.validator.beans.configuration;

import com.google.auto.value.AutoValue;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@AutoValue
@ConfigurationProperties("bare.metal.aliyun")
public class BareMetalConfiguration {

  private String apiUrl;

  public String getApiUrl() {
    return apiUrl;
  }

  public void setApiUrl(String apiUrl) {
    this.apiUrl = apiUrl;
  }
}
