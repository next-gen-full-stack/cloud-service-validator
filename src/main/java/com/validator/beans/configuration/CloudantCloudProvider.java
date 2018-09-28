package com.validator.beans.configuration;

import java.util.List;
import org.springframework.stereotype.Component;

@Component
public class CloudantCloudProvider {

  private String id;
  private String group;
  private List<CloudantEndpoint> endpoints;

  public String getId() {
    return this.id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getGroup() {
    return this.group;
  }

  public void setGroup(String group) {
    this.group = group;
  }

  public List<CloudantEndpoint> getEndPoints() {
    return this.endpoints;
  }

  public void setEndPoints(List<CloudantEndpoint> endpoints) {
    this.endpoints = endpoints;
  }
}
