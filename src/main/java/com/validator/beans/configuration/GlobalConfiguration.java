package com.validator.beans.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties("global")
public class GlobalConfiguration {

  private String id;
  private String location;
  private String region;

  private String alicloud;
  private String azure;

  public String getId() {
    return this.id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getLocation() {
    return this.location;
  }

  public void setLocation(String location) {
    this.location = location;
  }

  public String getRegion() {
    return this.region;
  }

  public void setRegion(String region) {
    this.region = region;
  }

  public String getAzure() {
    return this.azure;
  }

  public void setAzure(String azure) {
    this.azure = azure;
  }

  public String getAlicloud() {
    return this.alicloud;
  }

  public void setAlicloud(String alicloud) {
    this.alicloud = alicloud;
  }
}
