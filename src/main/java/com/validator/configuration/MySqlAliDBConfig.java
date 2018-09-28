package com.validator.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "spring.ds.ali.mysql")
public class MySqlAliDBConfig extends DBConfig {}
