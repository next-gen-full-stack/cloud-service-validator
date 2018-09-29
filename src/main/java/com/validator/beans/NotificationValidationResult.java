package com.validator.beans;


import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.exceptions.ServerException;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.http.ProtocolType;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;
import com.aliyuncs.push.model.v20160801.PushNoticeToAndroidRequest;
import com.aliyuncs.push.model.v20160801.PushNoticeToAndroidResponse;
import com.validator.beans.base.ServiceValidationResult;
import com.windowsazure.messaging.BaiduRegistration;
import com.windowsazure.messaging.Notification;
import com.windowsazure.messaging.NotificationHub;
import com.windowsazure.messaging.NotificationHubsException;

public class NotificationValidationResult extends ServiceValidationResult {

	 protected static String region;
	 protected static long appKey;
	 protected static String deviceIds;
	 protected static String deviceId;
	 protected static String accounts;
	 protected static String account;
	 protected static String aliases;
	 protected static String alias;
	 protected static String tag;
	 protected static String tagExpression;

	    protected static DefaultAcsClient client;

	public String azurePing() {
		String content="";
		try{
		String baiduid = "1129679776671371757";
		String channelid = "4566719708128378020";

		NotificationHub hub = new NotificationHub("Endpoint=sb://notification.servicebus.chinacloudapi.cn/;SharedAccessKeyName=DefaultFullSharedAccessSignature;SharedAccessKey=zkCl062hhgqCDngeIO10Kogf2Gyatj5+X3IZ3MzEYAU=", "notification-test");

		BaiduRegistration reg = new BaiduRegistration(baiduid,channelid);

		reg.setTagsFromString("tagtest");

		hub.createRegistration(reg);

		//发送测试
		String message = "{\"title\":\"Test Send message from server sdk\",\"description\":\"get message from server sdk !\"}";

		//发送测试
		Notification n = Notification.createBaiduNotifiation(message);

		hub.sendNotification(n);
		}catch(Exception e){
			e.printStackTrace();
		}
		return content;
	}
	
	public String aliPing() throws ServerException, ClientException, IOException{
		String content="";
		
		InputStream inputStream = NotificationValidationResult.class.getClassLoader().getResourceAsStream("application.properties");
        Properties properties = new Properties();
        properties.load(inputStream);

        String accessKeyId = properties.getProperty("notification.aliyun.accessKeyId");

        String accessKeySecret = properties.getProperty("notification.aliyun.accessKeySecret");

        String key = properties.getProperty("notification.aliyun.appKey");

        region = properties.getProperty("notification.aliyun.regionId");
        appKey = Long.valueOf(key);
        deviceIds = properties.getProperty("notification.aliyun.deviceIds");
        deviceId = properties.getProperty("notification.aliyun.deviceId");
        accounts = properties.getProperty("notification.aliyun.accounts");
        account = properties.getProperty("notification.aliyun.account");
        aliases = properties.getProperty("notification.aliyun.aliases");
        alias = properties.getProperty("notification.aliyun.tag");
        tag = properties.getProperty("notification.aliyun.alias");
        tagExpression = properties.getProperty("notification.aliyun.tagExpression");

        IClientProfile profile = DefaultProfile.getProfile(region, accessKeyId, accessKeySecret);
        client = new DefaultAcsClient(profile);
		
		
		
		
		PushNoticeToAndroidRequest androidRequest = new PushNoticeToAndroidRequest();
        //安全性比较高的内容建议使用HTTPS
        androidRequest.setProtocol(ProtocolType.HTTPS);
        //内容较大的请求，使用POST请求
        androidRequest.setMethod(MethodType.POST);
        androidRequest.setAppKey(appKey);
        androidRequest.setTarget("TAG");
        androidRequest.setTargetValue("tag1");
        androidRequest.setTitle("from sdk");
        androidRequest.setBody("send message from sdk");
        androidRequest.setExtParameters("{\"k1\":\"v1\"}");

        PushNoticeToAndroidResponse pushNoticeToAndroidResponse = client.getAcsResponse(androidRequest);
        System.out.printf("RequestId: %s, MessageId: %s\n",
                pushNoticeToAndroidResponse.getRequestId(), pushNoticeToAndroidResponse.getMessageId());
        return content;
	}
	
	public static void main(String[] args) throws NotificationHubsException, ServerException, ClientException, IOException {
		NotificationValidationResult result =new NotificationValidationResult();
		
		result.aliPing();
	}
}
