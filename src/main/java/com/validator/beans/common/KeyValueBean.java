package com.validator.beans.common;

public class KeyValueBean {
  private String key;
  private String value;

  public void setKey(String key) {
    this.key = key;
  }

  public String getKey() {
    return this.key;
  }

  public void setValue(String val) {
    this.value = val;
  }

  public String getValue() {
    return this.value;
  }

  public KeyValueBean(String key, String val) {
    this.setKey(key);
    this.setValue(val);
  }
}
