package com.validator.controllers.api;

import com.validator.beans.*;
import javax.servlet.http.HttpServletRequest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@ComponentScan("com.validator.beans")
public class K8sController {

  private final K8sValidationResult k8sValidationResult;

  public K8sController(K8sValidationResult serviceValidationResult) {
    this.k8sValidationResult = serviceValidationResult;
  }

  /**
   * This is in the environment that we're going to access and validate
   *
   * @param request
   * @return
   */
  @RequestMapping("/api/v1/ping/k8s")
  K8sValidationResult k8s(HttpServletRequest request) {

    return this.k8sValidationResult;
  }
}
