package com.validator.beans;

import com.google.auto.value.AutoValue;
import com.validator.beans.base.ServiceValidationResult;
import java.io.IOException;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@AutoValue
@ConfigurationProperties("apigateway")
public class ApiGatewayValidationResult extends ServiceValidationResult {
  // private static Logger LOGGER = LoggerFactory.getLogger(ApiGatewayValidationResult.class);
  private Long responseTime;
  private String output;
  private String azureUrl;
  private String aliyunUrl;

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

  public String ping() {
    String content = "";
    /*
    try {
      CloseableHttpClient httpClient = HttpClients.createDefault();
      // 创建请求方法的实例， 并指定请求url
      HttpGet httpget = new HttpGet(azureUrl);
      // 获取http响应状态码
      CloseableHttpResponse response = httpClient.execute(httpget);
      HttpEntity entity = response.getEntity();
      if (response.getStatusLine().getStatusCode() == 200) {
        this.setAccessibility(true);
      } else {
        this.setAccessibility(false);
      }
      // 接收响应头
      content = response.getStatusLine() + ":" + EntityUtils.toString(entity, "utf-8");
      System.out.println(content);
      httpClient.close();
    } catch (Exception e) {

    }
    */
    return content;
  }

  public String aliyunPing() {
     String content="";
    // HTTP Client init
     /*
    HttpClientBuilderParams httpParam = new HttpClientBuilderParams();
    httpParam.setAppKey("25090597");
    httpParam.setAppSecret("cc55603d37c21dab8bb181d60ba42866");
    HttpApiClient.getInstance().init(httpParam);

    HttpApiClient.getInstance()
        .apiDemo(
            new ApiCallback() {
              public void onFailure(ApiRequest request, Exception e) {
                e.printStackTrace();
              }

              public void onResponse(ApiRequest request, ApiResponse response) {
                try {
                  System.out.println(getResultString(response));
                  //content=getResultString(response);
                } catch (Exception ex) {
                  ex.printStackTrace();
                }
              }
            });
  */
    return content;
  }

  /*
  public String getResultString(ApiResponse response) throws IOException {
    StringBuilder result = new StringBuilder();
    result
        .append("Response from backend server")
        .append(SdkConstant.CLOUDAPI_LF)
        .append(SdkConstant.CLOUDAPI_LF);
    result
        .append("ResultCode:")
        .append(SdkConstant.CLOUDAPI_LF)
        .append(response.getCode())
        .append(SdkConstant.CLOUDAPI_LF)
        .append(SdkConstant.CLOUDAPI_LF);
    if (response.getCode() != 200) {
      result
          .append("Error description:")
          .append(response.getHeaders().get("X-Ca-Error-Message"))
          .append(SdkConstant.CLOUDAPI_LF)
          .append(SdkConstant.CLOUDAPI_LF);
    }

    result
        .append("ResultBody:")
        .append(SdkConstant.CLOUDAPI_LF)
        .append(new String(response.getBody(), SdkConstant.CLOUDAPI_ENCODING));
    System.out.println(""+response.getCode());
    this.setOutput(result.toString());
    return result.toString();
  }
  */
}
