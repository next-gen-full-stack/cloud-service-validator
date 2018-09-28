package com.validator.beans;

import com.validator.beans.base.ServiceValidationResult;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class DBValidationResult extends ServiceValidationResult {
  private Long responseTime;

  public Long getResponseTime() {
    return responseTime;
  }

  public void setResponseTime(Long responseTime) {
    this.responseTime = responseTime;
  }

  @Bean(name = "mysqlAliDBValidationResult")
  @ConfigurationProperties("mysql.ali")
  public DBValidationResult mysqlAliDBValidationResult() {
    return new DBValidationResult();
  }

  @Bean(name = "postgresqlAliDBValidationResult")
  @ConfigurationProperties("post.ali")
  public DBValidationResult postgresqlAliDBValidationResult() {
    return new DBValidationResult();
  }

  @Bean(name = "mysqlAzureDBValidationResult")
  @ConfigurationProperties("mysql.azure")
  public DBValidationResult mysqlAzureDBValidationResult() {
    return new DBValidationResult();
  }

  @Bean(name = "postgresqlAzureDBValidationResult")
  @ConfigurationProperties("post.azure")
  public DBValidationResult postgresqlAzureDBValidationResult() {
    return new DBValidationResult();
  }
}
