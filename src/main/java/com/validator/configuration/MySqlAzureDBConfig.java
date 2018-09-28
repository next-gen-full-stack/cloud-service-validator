package com.validator.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "spring.ds.azure.mysql")
public class MySqlAzureDBConfig extends DBConfig {}
