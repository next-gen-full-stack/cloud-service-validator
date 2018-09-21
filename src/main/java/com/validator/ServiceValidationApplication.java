package com.validator;

import org.springframework.boot.*;
import org.springframework.boot.autoconfigure.*;

@SpringBootApplication
public class ServiceValidationApplication {

  public static void main(String[] args) throws Exception {

    SpringApplication app = new SpringApplication(ServiceValidationApplication.class);

    app.run(args);
  }
}
