package com.validator.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DBConfig {
  private String url;
  private String username;
  private String password;
  private String driverClassName;

  public String getUrl() {
    return url;
  }

  public void setUrl(String url) {
    this.url = url;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public String getDriverClassName() {
    return driverClassName;
  }

  public void setDriverClassName(String driverClassName) {
    this.driverClassName = driverClassName;
  }

  @Bean(name = "mySqlAliDBConfig")
  @ConfigurationProperties(prefix = "spring.ds.ali.mysql")
  public DBConfig mySqlAliDBConfig() {
    return new DBConfig();
  }

  @Bean(name = "mySqlAzureDBConfig")
  @ConfigurationProperties(prefix = "spring.ds.azure.mysql")
  public DBConfig mySqlAzureDBConfig() {
    return new DBConfig();
  }

  @Bean(name = "postgresqlAliDBConfig")
  @ConfigurationProperties(prefix = "spring.ds.ali.post")
  public DBConfig postgresqlAliDBConfig() {
    return new DBConfig();
  }

  @Bean(name = "postgresqlAzureDBConfig")
  @ConfigurationProperties(prefix = "spring.ds.azure.post")
  public DBConfig postgresqlAzureDBConfig() {
    return new DBConfig();
  }
}
