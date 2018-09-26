package com.validator.controllers.api;

import com.alibaba.cloudapi.sdk.model.ApiCallback;
import com.alibaba.cloudapi.sdk.model.ApiRequest;
import com.alibaba.cloudapi.sdk.model.ApiResponse;
import com.validator.beans.ApiGatewayValidationResult;
import com.validator.beans.HttpApiClient;

import java.time.Duration;
import java.time.LocalDateTime;
import javax.servlet.http.HttpServletRequest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@ComponentScan("com.validator.beans")
public class ApiGatewayController {

  private final ApiGatewayValidationResult apiGatewayValidationResult;

  public ApiGatewayController(ApiGatewayValidationResult serviceValidationResult) {
    this.apiGatewayValidationResult = serviceValidationResult;
  }

  @RequestMapping("/api/v1/ping/azureApigateway")
  ApiGatewayValidationResult azureApiGateway(HttpServletRequest request) {
    try {
      apiGatewayValidationResult.setService("azureApigateway");
      LocalDateTime oldDate = LocalDateTime.now();
      String result = this.apiGatewayValidationResult.ping();
      LocalDateTime newDate = LocalDateTime.now();
      // count seconds between dates
      Duration duration = Duration.between(oldDate, newDate);
      this.apiGatewayValidationResult.setResponseTime(duration.toMillis());
      this.apiGatewayValidationResult.setOutput(result);
    } catch (Exception e) {
      System.err.println(e.getClass().getName() + ":[EXCEPtION] " + e.getMessage());
    }
    return apiGatewayValidationResult;
  }

  @RequestMapping("/api/v1/ping/aliyunApigateway")
  ApiGatewayValidationResult aliyunApiGateway(HttpServletRequest request) {
    try {
      apiGatewayValidationResult.setService("aliyunApigateway");
      apiGatewayValidationResult.aliyunPing();
      HttpApiClient.getInstance()
      .apiDemo(
          new ApiCallback() {
            public void onFailure(ApiRequest request, Exception e) {
              e.printStackTrace();
            }

            public void onResponse(ApiRequest request, ApiResponse response) {
              try {
                System.out.println(apiGatewayValidationResult.getResultString(response));
                //content=getResultString(response);
              } catch (Exception ex) {
                ex.printStackTrace();
              }
            }
          });
    } catch (Exception e) {
      System.err.println(e.getClass().getName() + ":[EXCEPtION] " + e.getMessage());
    }
    return apiGatewayValidationResult;
  }
}