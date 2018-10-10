package com.validator.testEncryption.aliyun;

import com.aliyun.oss.HttpMethod;
import com.aliyun.oss.OSSClient;
import com.aliyun.oss.common.utils.DateUtil;
import com.aliyun.oss.model.GeneratePresignedUrlRequest;
import com.aliyun.oss.model.PutObjectResult;
import com.validator.testEncryption.BlobBasics;
import java.io.File;
import java.io.FileInputStream;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestAliEncryptionController {

  @RequestMapping("/testAliEncryption")
  public String testAliEncryption() throws Exception {

    String endpoint = "http://oss-cn-beijing.aliyuncs.com";
    String accessKeyId = "LTAIPckWV5IHcny3";
    String accessKeySecret = "hxzEydhgkJnvY6bAlPOzS3LwMtoZYb";
    String bucketName = "testencrypt";

    SimpleDateFormat df = new SimpleDateFormat("MMddHHmmss");
    String objectName = "helloSample" + df.format(new Date());

    // 创建OSSClient实例。
    OSSClient ossClient = new OSSClient(endpoint, accessKeyId, accessKeySecret);
    Date expiration = DateUtil.parseRfc822Date("Thu, 19 Mar 2019 18:00:00 GMT");
    // 生成签名URL。
    GeneratePresignedUrlRequest request =
        new GeneratePresignedUrlRequest(bucketName, objectName, HttpMethod.PUT);
    // 设置过期时间。
    request.setExpiration(expiration);
    // 设置Content-Type。
    request.setContentType("application/octet-stream");
    // 添加用户自定义元信息。
    request.addUserMetadata("author", "derek");
    // 生成签名URL（HTTP PUT请求）。
    URL signedUrl = ossClient.generatePresignedUrl(request);
    System.out.println("signed url for putObject: " + signedUrl);
    // 使用签名URL发送请求。
    // 关闭OSSClient。
    ossClient.shutdown();

    File sampleFile = BlobBasics.createTempLocalFile("blockblob", ".tmp", (10000 * 1024));
    //        FileInputStream fin = new FileInputStream(sampleFile);

    CloseableHttpClient client = HttpClients.createDefault();
    HttpPut httpPut = new HttpPut(signedUrl.toString());

    MultipartEntityBuilder builder = MultipartEntityBuilder.create();
    builder.addBinaryBody("file", sampleFile, ContentType.APPLICATION_OCTET_STREAM, "file.ext");
    HttpEntity multipart = builder.build();
    //
    httpPut.setEntity(multipart);
    httpPut.setHeader("Content-Type", "application/octet-stream");
    httpPut.setHeader("x-oss-meta-author", "derek");

    long start = Calendar.getInstance().getTimeInMillis();
    CloseableHttpResponse response = client.execute(httpPut);
    long end = Calendar.getInstance().getTimeInMillis();
    double spentTime = (double) end - start;
    client.close();

    System.out.println("Successfully uploaded the blob.");
    String retStr =
        "Successfully uploaded Object in Ali Cloud Bucket:["
            + bucketName
            + "] with file name:"
            + objectName;
    retStr += "<br> File size: 10.0M <br>Spent time: " + Math.round(spentTime) + "ms";
    System.out.println("Success.");
    return retStr;

    //        HttpClient httpclient = new DefaultHttpClient();
    //        try {
    //        	HttpPost httppost = new HttpPost(signedUrl);
    //        	FileBody bin = new FileBody(new File(filepath + File.separator + filename1));
    //        	FileBody bin2 = new FileBody(new File(filepath + File.separator + filename2));
    //        	StringBody comment = new StringBody(filename1); 		    MultipartEntity reqEntity = new
    // MultipartEntity();
    //        	reqEntity.addPart("file1", bin);//file1为请求后台的File upload;属性
    // reqEntity.addPart("file2", bin2);//file2为请求后台的File upload;属性
    // reqEntity.addPart("filename1", comment);//filename1为请求后台的普通参数;属性
    // httppost.setEntity(reqEntity);		    		    HttpResponse response =
    // httpclient.execute(httppost);		    			    		    int statusCode =
    // response.getStatusLine().getStatusCode();		    			    			if(statusCode == HttpStatus.SC_OK){
    //		    					System.out.println("服务器正常响应.....");						    	HttpEntity resEntity =
    // response.getEntity();
    //	System.out.println(EntityUtils.toString(resEntity));//httpclient自带的工具类读取返回数据
    //   			    	System.out.println(resEntity.getContent());
    //	EntityUtils.consume(resEntity);		    }			    			} catch (ParseException e) {				// TODO
    // Auto-generated catch block				e.printStackTrace();			} catch (IOException e) {				// TODO
    // Auto-generated catch block				e.printStackTrace();			} finally {			    try {
    //	httpclient.getConnectionManager().shutdown(); 			    } catch (Exception ignore) {
    //   }			}
    //        }

    // *************

    // 添加PutObject请求头。
    //        Map<String, String> customHeaders = new HashMap<String, String>();
    //        customHeaders.put("Content-Type", "application/octet-stream");
    //        customHeaders.put("x-oss-meta-author", "derek");
    //        PutObjectResult result = ossClient.putObject(signedUrl, fin, sampleFile.length(),
    // customHeaders);

  }

  public static void main(String[] args) throws Exception {

    String endpoint = "http://oss-cn-beijing.aliyuncs.com";
    String accessKeyId = "LTAIPckWV5IHcny3";
    String accessKeySecret = "hxzEydhgkJnvY6bAlPOzS3LwMtoZYb";
    String bucketName = "testencrypt";

    SimpleDateFormat df = new SimpleDateFormat("MMddHHmmss");
    String objectName = "helloSample" + df.format(new Date());

    // 创建OSSClient实例。
    OSSClient ossClient = new OSSClient(endpoint, accessKeyId, accessKeySecret);
    Date expiration = DateUtil.parseRfc822Date("Thu, 19 Mar 2019 18:00:00 GMT");
    // 生成签名URL。
    GeneratePresignedUrlRequest request =
        new GeneratePresignedUrlRequest(bucketName, objectName, HttpMethod.PUT);
    // 设置过期时间。
    request.setExpiration(expiration);
    // 设置Content-Type。
    request.setContentType("application/octet-stream");
    // 添加用户自定义元信息。
    request.addUserMetadata("author", "derek");
    // 生成签名URL（HTTP PUT请求）。
    URL signedUrl = ossClient.generatePresignedUrl(request);
    System.out.println("signed url for putObject: " + signedUrl);
    // 使用签名URL发送请求。

    File sampleFile = BlobBasics.createTempLocalFile("blockblob", ".tmp", (128 * 1024));
    FileInputStream fin = new FileInputStream(sampleFile);
    // 添加PutObject请求头。
    Map<String, String> customHeaders = new HashMap<String, String>();
    customHeaders.put("Content-Type", "application/octet-stream");
    customHeaders.put("x-oss-meta-author", "derek");
    PutObjectResult result =
        ossClient.putObject(signedUrl, fin, sampleFile.length(), customHeaders);
    // 关闭OSSClient。
    ossClient.shutdown();
    System.out.println("Success.");
  }
}
