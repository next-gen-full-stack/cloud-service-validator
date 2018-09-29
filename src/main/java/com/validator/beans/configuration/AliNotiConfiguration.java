package com.validator.beans.configuration;

import com.google.auto.value.AutoValue;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@AutoValue
@ConfigurationProperties("notification.aliyun")
public class AliNotiConfiguration {

  private String accessKeyId;
  private String accessKeySecret;
  private String appKey;
  private String deviceIds;
  private String accounts;
  private String account;
  private String aliases;
  private String alias;
  private String tag;
  private String tagExpression;
  private String regionId;

  public String getAccessKeyId() {
    return accessKeyId;
  }

  public void setAccessKeyId(String accessKeyId) {
    this.accessKeyId = accessKeyId;
  }

  public String getAccessKeySecret() {
    return accessKeySecret;
  }

  public void setAccessKeySecret(String accessKeySecret) {
    this.accessKeySecret = accessKeySecret;
  }

  public String getAppKey() {
    return appKey;
  }

  public void setAppKey(String appKey) {
    this.appKey = appKey;
  }

  public String getDeviceIds() {
    return deviceIds;
  }

  public void setDeviceIds(String deviceIds) {
    this.deviceIds = deviceIds;
  }

  public String getAccounts() {
    return accounts;
  }

  public void setAccounts(String accounts) {
    this.accounts = accounts;
  }

  public String getAccount() {
    return account;
  }

  public void setAccount(String account) {
    this.account = account;
  }

  public String getAliases() {
    return aliases;
  }

  public void setAliases(String aliases) {
    this.aliases = aliases;
  }

  public String getAlias() {
    return alias;
  }

  public void setAlias(String alias) {
    this.alias = alias;
  }

  public String getTag() {
    return tag;
  }

  public void setTag(String tag) {
    this.tag = tag;
  }

  public String getTagExpression() {
    return tagExpression;
  }

  public void setTagExpression(String tagExpression) {
    this.tagExpression = tagExpression;
  }

  public String getRegionId() {
    return regionId;
  }

  public void setRegionId(String regionId) {
    this.regionId = regionId;
  }
}
