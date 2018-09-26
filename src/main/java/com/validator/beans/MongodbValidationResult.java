package com.validator.beans;

import com.google.auto.value.AutoValue;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.validator.beans.base.ServiceValidationResult;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@AutoValue
@ConfigurationProperties("cosmosdb")
public class MongodbValidationResult extends ServiceValidationResult {

  private String connectUrl;
  private Long responseTime;
  private MongoClient mongoClient;

  /** @param connectUrl the connectUrl to set */
  public void setConnectUrl(String connectUrl) {
    this.connectUrl = connectUrl;
  }

  public MongoClient MGDBClient() {
    if (this.mongoClient == null) {
      MongoClientURI uri = new MongoClientURI(this.connectUrl);
      MongoClient mongoClient = new MongoClient(uri);
      this.mongoClient = mongoClient;
    }
    return this.mongoClient;
  }

  /** @return the responseTime */
  public Long getResponseTime() {
    return responseTime;
  }

  /** @param responseTime the responseTime to set */
  public void setResponseTime(Long responseTime) {
    this.responseTime = responseTime;
  }
}
