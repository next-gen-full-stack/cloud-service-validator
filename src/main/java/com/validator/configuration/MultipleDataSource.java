package com.validator.configuration;

import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;

@Configuration
public class MultipleDataSource {
  @Autowired private DBConfig mySqlAliDBConfig;
  @Autowired private DBConfig postgresqlAliDBConfig;

  @Autowired private DBConfig postgresqlAzureDBConfig;

  @Autowired private DBConfig mySqlAzureDBConfig;

  @Bean(name = "mysqlAliDB")
  public DataSource mysqlAliDataSource() {
    return DataSourceBuilder.create()
        .url(mySqlAliDBConfig.getUrl())
        .username(mySqlAliDBConfig.getUsername())
        .password(mySqlAliDBConfig.getPassword())
        .driverClassName(mySqlAliDBConfig.getDriverClassName())
        .build();
  }

  @Bean(name = "postgresqlAliDB")
  public DataSource postgresqlAliDataSource() {
    return DataSourceBuilder.create()
        .url(postgresqlAliDBConfig.getUrl())
        .username(postgresqlAliDBConfig.getUsername())
        .password(postgresqlAliDBConfig.getPassword())
        .driverClassName(postgresqlAliDBConfig.getDriverClassName())
        .build();
  }

  @Bean(name = "postgresqlAzureDB")
  public DataSource postgresqlAzureDataSource() {
    return DataSourceBuilder.create()
        .url(postgresqlAzureDBConfig.getUrl())
        .username(postgresqlAzureDBConfig.getUsername())
        .password(postgresqlAzureDBConfig.getPassword())
        .driverClassName(postgresqlAzureDBConfig.getDriverClassName())
        .build();
  }

  @Bean(name = "mysqlAzureDB")
  public DataSource mysqlAzureDataSource() {
    return DataSourceBuilder.create()
        .url(mySqlAzureDBConfig.getUrl())
        .username(mySqlAzureDBConfig.getUsername())
        .password(mySqlAzureDBConfig.getPassword())
        .driverClassName(mySqlAzureDBConfig.getDriverClassName())
        .build();
  }

  @Bean(name = "mysqlAliJdbcTemplate")
  public JdbcTemplate mysqlAliJdbcTemplate(@Qualifier("mysqlAliDB") DataSource dsMySQL) {
    return new JdbcTemplate(dsMySQL);
  }

  @Bean(name = "mysqlAzureJdbcTemplate")
  public JdbcTemplate mysqlAzureJdbcTemplate(@Qualifier("mysqlAzureDB") DataSource dsMySQL) {
    return new JdbcTemplate(dsMySQL);
  }

  @Bean(name = "postgresqlAliJdbcTemplate")
  public JdbcTemplate postgresqlAliJdbcTemplate(
      @Qualifier("postgresqlAliDB") DataSource postgreSQL) {
    return new JdbcTemplate(postgreSQL);
  }

  @Bean(name = "postgresqlAzureJdbcTemplate")
  public JdbcTemplate postgresqlAzureJdbcTemplate(
      @Qualifier("postgresqlAzureDB") DataSource postgreSQL) {
    return new JdbcTemplate(postgreSQL);
  }
}
