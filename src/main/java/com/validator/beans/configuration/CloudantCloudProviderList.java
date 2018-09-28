package com.validator.beans.configuration;

import java.util.List;
import org.springframework.stereotype.Component;

@Component
public class CloudantCloudProviderList {

  private List<CloudantCloudProvider> list;

  public List<CloudantCloudProvider> getList() {
    return this.list;
  }

  public void setList(List<CloudantCloudProvider> list) {
    this.list = list;
  }
}
