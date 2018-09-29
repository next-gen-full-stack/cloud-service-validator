package com.validator.util;

import com.alibaba.cloudapi.sdk.client.ApacheHttpClient;
import com.alibaba.cloudapi.sdk.constant.SdkConstant;
import com.alibaba.cloudapi.sdk.enums.HttpMethod;
import com.alibaba.cloudapi.sdk.enums.Scheme;
import com.alibaba.cloudapi.sdk.model.ApiRequest;
import com.alibaba.cloudapi.sdk.model.ApiResponse;
import com.alibaba.cloudapi.sdk.model.HttpClientBuilderParams;
import com.validator.beans.ApiGatewayValidationResult;
import java.time.Duration;
import java.time.LocalDateTime;

public class HttpApiClient extends ApacheHttpClient {
  static HttpApiClient instance = new HttpApiClient();

  public static HttpApiClient getInstance() {
    return instance;
  }

  public void init(HttpClientBuilderParams httpClientBuilderParams) {
    httpClientBuilderParams.setScheme(Scheme.HTTP);
    super.init(httpClientBuilderParams);
  }

  public void apiDemo(String path, ApiGatewayValidationResult apiGatewayValidationResult) {
    ApiRequest request = new ApiRequest(HttpMethod.GET, path);
    // sendAsyncRequest(request, callback);
    LocalDateTime oldDate = LocalDateTime.now();
    ApiResponse apiResponse = sendSyncRequest(request);
    LocalDateTime newDate = LocalDateTime.now();
    System.out.println(apiResponse.getCode());
    if (200 == apiResponse.getCode()) {
      String output = new String(apiResponse.getBody(), SdkConstant.CLOUDAPI_ENCODING);
      apiGatewayValidationResult.setAccessibility(true);
      apiGatewayValidationResult.setScalability(true);
      apiGatewayValidationResult.setOutput(output);
    } else {
      apiGatewayValidationResult.setAccessibility(false);
      apiGatewayValidationResult.setScalability(true);
    }
    Duration duration = Duration.between(oldDate, newDate);
    apiGatewayValidationResult.setResponseTime(duration.toMillis());
    System.out.println("" + new String(apiResponse.getBody(), SdkConstant.CLOUDAPI_ENCODING));
  }
}
