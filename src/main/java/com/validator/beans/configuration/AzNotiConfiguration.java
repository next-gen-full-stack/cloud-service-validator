package com.validator.beans.configuration;

import com.google.auto.value.AutoValue;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@AutoValue
@ConfigurationProperties("notification.azure")
public class AzNotiConfiguration {

  private String baiduid;
  private String channelid;
  private String endpoint;
  private String appName;

  public String getBaiduid() {
    return baiduid;
  }

  public void setBaiduid(String baiduid) {
    this.baiduid = baiduid;
  }

  public String getChannelid() {
    return channelid;
  }

  public void setChannelid(String channelid) {
    this.channelid = channelid;
  }

  public String getEndpoint() {
    return endpoint;
  }

  public void setEndpoint(String endpoint) {
    this.endpoint = endpoint;
  }

  public String getAppName() {
    return appName;
  }

  public void setAppName(String appName) {
    this.appName = appName;
  }
}
