package com.validator.beans;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.http.ProtocolType;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;
import com.aliyuncs.push.model.v20160801.PushNoticeToAndroidRequest;
import com.aliyuncs.push.model.v20160801.PushNoticeToAndroidResponse;
import com.google.auto.value.AutoValue;
import com.validator.beans.base.ServiceValidationResult;
import com.validator.beans.configuration.AliNotiConfiguration;
import com.validator.beans.configuration.AzNotiConfiguration;
import com.windowsazure.messaging.BaiduRegistration;
import com.windowsazure.messaging.Notification;
import com.windowsazure.messaging.NotificationHub;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@AutoValue
@ConfigurationProperties("notification")
public class NotificationValidationResult extends ServiceValidationResult {

  private Long responseTime;

  public Long getResponseTime() {
    return responseTime;
  }

  public void setResponseTime(Long responseTime) {
    this.responseTime = responseTime;
  }

  protected static DefaultAcsClient client;

  public boolean azurePing(AzNotiConfiguration azConfig) {
    try {
      String baiduid = azConfig.getBaiduid();
      String channelid = azConfig.getChannelid();

      NotificationHub hub = new NotificationHub(azConfig.getEndpoint(), azConfig.getAppName());
      BaiduRegistration reg = new BaiduRegistration(baiduid, channelid);
      // reg.setTagsFromString("tagtest");
      hub.createRegistration(reg);
      // 发送测试
      String message =
          "{\"title\":\"Test Send message from server sdk\",\"description\":\"get message from server sdk !\"}";
      // 发送测试
      Notification n = Notification.createBaiduNotifiation(message);

      hub.sendNotification(n);
    } catch (Exception e) {
      e.printStackTrace();
      return false;
    }
    return true;
  }

  public boolean aliPing(AliNotiConfiguration aliConfig) {

    try {
      String accessKeyId = aliConfig.getAccessKeyId();
      String accessKeySecret = aliConfig.getAccessKeySecret();
      String key = aliConfig.getAppKey();
      Long appKey = Long.valueOf(key);
      String region = aliConfig.getRegionId();

      IClientProfile profile = DefaultProfile.getProfile(region, accessKeyId, accessKeySecret);
      client = new DefaultAcsClient(profile);

      PushNoticeToAndroidRequest androidRequest = new PushNoticeToAndroidRequest();
      // 安全性比较高的内容建议使用HTTPS
      androidRequest.setProtocol(ProtocolType.HTTPS);
      // 内容较大的请求，使用POST请求
      androidRequest.setMethod(MethodType.POST);
      androidRequest.setAppKey(appKey);
      androidRequest.setTarget("TAG");
      androidRequest.setTargetValue("tag1");
      androidRequest.setTitle("aliyun push notificaiton");
      androidRequest.setBody("send message from sdk");
      androidRequest.setExtParameters("{\"k1\":\"v1\"}");

      PushNoticeToAndroidResponse pushNoticeToAndroidResponse =
          client.getAcsResponse(androidRequest);
      System.out.printf(
          "RequestId: %s, MessageId: %s\n",
          pushNoticeToAndroidResponse.getRequestId(), pushNoticeToAndroidResponse.getMessageId());
    } catch (Exception e) {
      e.printStackTrace();
      return false;
    }
    return true;
  }
}
