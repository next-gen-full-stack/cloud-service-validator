package com.validator.beans.base;

public abstract class ServiceValidationResult {

  private String service;
  private String location;
  private Boolean scalability;
  private Boolean accessibility;

  public String getService() {
    return this.service;
  }

  public void setService(String service) {
    this.service = service;
  }

  public String getLocation() {
    return this.location;
  }

  public void setLocation(String location) {
    this.location = location;
  }

  public Boolean getScalability() {
    return this.scalability;
  }

  public void setScalability(Boolean scalability) {
    this.scalability = scalability;
  }

  public Boolean getAccessibility() {
    return this.accessibility;
  }

  public void setAccessibility(Boolean accessibility) {
    this.accessibility = accessibility;
  }
}
