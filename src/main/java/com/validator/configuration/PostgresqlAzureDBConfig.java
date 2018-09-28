package com.validator.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "spring.ds.azure.post")
public class PostgresqlAzureDBConfig extends DBConfig {}
