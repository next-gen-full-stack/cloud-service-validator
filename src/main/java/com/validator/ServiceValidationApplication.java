package com.validator;

import org.springframework.boot.*;
import org.springframework.boot.autoconfigure.*;
import org.springframework.boot.autoconfigure.data.mongo.MongoDataAutoConfiguration;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;

@SpringBootApplication(exclude = {MongoAutoConfiguration.class, MongoDataAutoConfiguration.class})
public class ServiceValidationApplication {

  public static void main(String[] args) throws Exception {

    SpringApplication app = new SpringApplication(ServiceValidationApplication.class);

    app.run(args);
  }
}
