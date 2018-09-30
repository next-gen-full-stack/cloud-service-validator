package com.validator.application.usecase;

import com.aliyun.openservices.ons.api.Message;
import com.aliyun.openservices.ons.api.ONSFactory;
import com.aliyun.openservices.ons.api.Producer;
import com.aliyun.openservices.ons.api.PropertyKeyConst;
import com.aliyun.openservices.ons.api.SendResult;
import java.io.IOException;
import java.util.Date;
import java.util.Properties;
import java.util.Random;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.stereotype.Component;

@Component
public class MQUsecase {

  public boolean testMQProducer() {
    Properties properties = new Properties();
    // 您在控制台创建的 Producer ID
    properties.put(PropertyKeyConst.ProducerId, "PID_TEST_PRODUCER_JACKY");
    // AccessKey 阿里云身份验证，在阿里云服务器管理控制台创建
    properties.put(PropertyKeyConst.AccessKey, "LTAIPckWV5IHcny3");
    // SecretKey 阿里云身份验证，在阿里云服务器管理控制台创建
    properties.put(PropertyKeyConst.SecretKey, "hxzEydhgkJnvY6bAlPOzS3LwMtoZYb");
    // 设置发送超时时间，单位毫秒
    properties.setProperty(PropertyKeyConst.SendMsgTimeoutMillis, "8000");
    // 设置 TCP 接入域名（此处以公共云生产环境为例）
    properties.put(
        PropertyKeyConst.ONSAddr,
        "http://onsaddr-internet.aliyun.com/rocketmq/nsaddr4client-internet");
    Producer producer = ONSFactory.createProducer(properties);
    // 在发送消息前，必须调用 start 方法来启动 Producer，只需调用一次即可
    producer.start();
    // 循环发送消息
    for (int i = 0; i < 2; i++) {
      Message msg =
          new Message( //
              // Message 所属的 Topic
              "test_mq_jacky",
              // Message Tag 可理解为 Gmail 中的标签，对消息进行再归类，方便 Consumer 指定过滤条件在 MQ 服务器过滤
              "TagA",
              // Message Body 可以是任何二进制形式的数据， MQ 不做任何干预，
              // 需要 Producer 与 Consumer 协商好一致的序列化和反序列化方式
              "Hello MQ".getBytes());
      // 设置代表消息的业务关键属性，请尽可能全局唯一。
      // 以方便您在无法正常收到消息情况下，可通过阿里云服务器管理控制台查询消息并补发
      // 注意：不设置也不会影响消息正常收发
      msg.setKey("ORDERID_" + i);
      try {
        SendResult sendResult = producer.send(msg);
        // 同步发送消息，只要不抛异常就是成功
        if (sendResult != null) {
          System.out.println(
              new Date()
                  + " Send mq message success. Topic is:"
                  + msg.getTopic()
                  + " msgId is: "
                  + sendResult.getMessageId());
        }
      } catch (Exception e) {
        // 消息发送失败，需要进行重试处理，可重新发送这条消息或持久化这条数据进行补偿处理
        System.out.println(new Date() + " Send mq message failed. Topic is:" + msg.getTopic());
        e.printStackTrace();
        return false;
      }
    }
    // 在应用退出前，销毁 Producer 对象
    // 注意：如果不销毁也没有问题
    producer.shutdown();
    return true;
  }

  public boolean testKafkaProducer(String brokers, String topicName) {
    // Set properties used to configure the producer
    Properties properties = new Properties();
    // Set the brokers (bootstrap servers)
    properties.setProperty("bootstrap.servers", brokers);
    // Set how to serialize key/value pairs
    properties.setProperty(
        "key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
    properties.setProperty(
        "value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
    // specify the protocol for Domain Joined clusters
    // properties.setProperty(CommonClientConfigs.SECURITY_PROTOCOL_CONFIG, "SASL_PLAINTEXT");

    KafkaProducer<String, String> producer = new KafkaProducer<>(properties);
    // So we can generate random sentences
    Random random = new Random();
    String[] sentences =
        new String[] {
          "the cow jumped over the moon",
          "an apple a day keeps the doctor away",
          "four score and seven years ago",
          "snow white and the seven dwarfs",
          "i am at two with nature"
        };

    String progressAnimation = "|/-\\";
    // Produce a bunch of records
    for (int i = 0; i < 2; i++) {
      // Pick a sentence at random
      String sentence = sentences[random.nextInt(sentences.length)];
      // Send the sentence to the test topic
      try {
        producer.send(new ProducerRecord<String, String>(topicName, sentence)).get();
      } catch (Exception ex) {
        System.out.println(ex.getMessage());
        return false;
      }
      String progressBar =
          "\r" + progressAnimation.charAt(i % progressAnimation.length()) + " " + i;
      try {
        System.out.write(progressBar.getBytes());
      } catch (IOException e) {
        // do nothing;
      }
    }
    return true;
  }
}
