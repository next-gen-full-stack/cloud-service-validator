package com.validator.beans;

import com.alibaba.cloudapi.sdk.model.HttpClientBuilderParams;
import com.google.auto.value.AutoValue;
import com.validator.beans.base.ServiceValidationResult;
import com.validator.util.HttpApiClient;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
@AutoValue
@ConfigurationProperties("apigateway")
public class ApiGatewayValidationResult extends ServiceValidationResult {
  // private static Logger LOGGER = LoggerFactory.getLogger(ApiGatewayValidationResult.class);
  private Long responseTime;
  private String output;
  private String azureUrl;
  private String aliyunUrl;
  private String aliyunPath;
  private String aliyunAppKey;
  private String aliyunAppSecret;

  public String getOutput() {
    return output;
  }

  public void setOutput(String output) {
    this.output = output;
  }

  public void setAzureUrl(String azureUrl) {
    this.azureUrl = azureUrl;
  }

  public void setAliyunUrl(String aliyunUrl) {
    this.aliyunUrl = aliyunUrl;
  }

  /** @param l the responseTime to set */
  public void setResponseTime(long l) {
    this.responseTime = l;
  }

  /** @return the responseTime */
  public Long getResponseTime() {
    return responseTime;
  }

  public void setAliyunPath(String aliyunPath) {
    this.aliyunPath = aliyunPath;
  }

  public void setAliyunAppKey(String aliyunAppKey) {
    this.aliyunAppKey = aliyunAppKey;
  }

  public void setAliyunAppSecret(String aliyunAppSecret) {
    this.aliyunAppSecret = aliyunAppSecret;
  }

  public String ping() {
    String content = "";
    RestTemplate restTemplate = new RestTemplate();
    try {
      K8sValidationResult k8sValidationResult =
          restTemplate.getForObject(azureUrl, K8sValidationResult.class, 200);
      content =
          "service:"
              + k8sValidationResult.getService()
              + ";location:"
              + k8sValidationResult.getLocation()
              + ";accessibility:"
              + k8sValidationResult.getAccessibility()
              + ";scalability:"
              + k8sValidationResult.getScalability();
      this.setAccessibility(true);
      this.setScalability(true);
    } catch (Exception e) {
      this.setAccessibility(false);
      this.setScalability(true);
      System.out.println("" + e.getMessage());
    }
    return content;
  }

  public void aliyunping() {
    try {
      // this.setService("aliyunApigateway");
      // this.setLocation("Alibaba Cloud");
      // apiGatewayValidationResult.aliyunPing();
      HttpClientBuilderParams httpParam = new HttpClientBuilderParams();
      httpParam.setHost(this.aliyunUrl);
      httpParam.setAppKey(this.aliyunAppKey);
      httpParam.setAppSecret(this.aliyunAppSecret);
      HttpApiClient.getInstance().init(httpParam);
      HttpApiClient.getInstance().apiDemo(this.aliyunPath, this);
    } catch (Exception e) {
      System.err.println(e.getClass().getName() + ":[EXCEPtION] " + e.getMessage());
    }
  }
}
