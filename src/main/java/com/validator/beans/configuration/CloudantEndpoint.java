package com.validator.beans.configuration;

import org.springframework.stereotype.Component;

@Component
public class CloudantEndpoint {

  private String name;
  private String path;

  public String getName() {
    return this.name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getPath() {
    return this.path;
  }

  public void setPath(String path) {
    this.path = path;
  }
}
