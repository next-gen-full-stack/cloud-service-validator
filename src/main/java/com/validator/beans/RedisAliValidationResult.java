package com.validator.beans;

import com.google.auto.value.AutoValue;
import com.validator.beans.base.ServiceValidationResult;
import io.lettuce.core.RedisClient;
import io.lettuce.core.RedisURI;
import io.lettuce.core.api.StatefulRedisConnection;
import io.lettuce.core.api.sync.RedisStringCommands;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@AutoValue
@ConfigurationProperties("redis.ali")
public class RedisAliValidationResult extends ServiceValidationResult {
  private static Logger LOGGER = LoggerFactory.getLogger(RedisValidationResult.class);

  private RedisClient redisClient;

  private String connectUrl;
  private String result;
  private Long responseTime;

  public void setRedisClient(RedisClient redisClient) {
    this.redisClient = redisClient;
  }

  /** @return the responseTime */
  public Long getResponseTime() {
    return responseTime;
  }

  /** @param l the responseTime to set */
  public void setResponseTime(long l) {
    this.responseTime = l;
  }

  /** @param result the result to set */
  public void setResult(String result) {
    this.result = result;
  }

  /** @param connectUrl the connectUrl to set */
  public void setConnectUrl(String connectUrl) {
    this.connectUrl = connectUrl;
  }

  public String ping() {
    if (this.redisClient == null) {
      RedisURI redisUri = RedisURI.create(connectUrl);
      RedisClient client = RedisClient.create(redisUri);
      this.redisClient = client;
    }
    StatefulRedisConnection<String, String> connection = this.redisClient.connect();
    LOGGER.info("Connected to Redis");
    RedisStringCommands sync = connection.sync();
    sync.set("key", "DaiVB Test Redis for Ali");
    LOGGER.info("Value Setting Successfully!");
    String value = (String) sync.get("key");
    LOGGER.info("Value Getting Successfully!");
    return value;
  }

  public void shutDown() {
    this.redisClient.shutdown();
  }
}
