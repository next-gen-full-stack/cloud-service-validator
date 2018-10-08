package com.validator.controllers.api;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.validator.beans.MongodbAliValidationResult;
import com.validator.beans.MongodbValidationResult;
import java.time.Duration;
import java.time.LocalDateTime;
import javax.servlet.http.HttpServletRequest;
import org.bson.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@ComponentScan("com.validator.beans")
public class MongodbController {

  private static Logger LOGGER = LoggerFactory.getLogger(MongodbValidationResult.class);
  private final MongodbValidationResult mgdbValidationResult;
  private final MongodbAliValidationResult alimgdbValidationResult;

  public MongodbController(MongodbValidationResult serviceValidationResult,
      MongodbAliValidationResult alivalidationResult) {
    this.mgdbValidationResult = serviceValidationResult;
    this.alimgdbValidationResult = alivalidationResult;
  }

  @RequestMapping("/api/v1/ping/mgdb")
  MongodbValidationResult mgdb(HttpServletRequest request) {
    try {
      MongoDatabase database = mgdbValidationResult.MGDBClient().getDatabase("testdb");
      MongoCollection<Document> collection = database.getCollection("items");
      LocalDateTime oldDate = LocalDateTime.now();
      LOGGER.info("AZure集合选择成功");

      Document myDoc = collection.find().first();
      LOGGER.info((String) myDoc.get("fruit"));
      LOGGER.info("文档读取成功");
      LocalDateTime newDate = LocalDateTime.now();
      // count seconds between dates
      Duration duration = Duration.between(oldDate, newDate);
      this.mgdbValidationResult.setResponseTime(duration.toMillis());
      this.mgdbValidationResult.setAccessibility(true);
    } catch (Exception e) {
      this.mgdbValidationResult.setAccessibility(false);
      LOGGER.error(e.getClass().getName() + ":[EXCEPtION] " + e.getMessage());
    }
    return this.mgdbValidationResult;
  }

  @RequestMapping("/api/v1/ping/ali/mongodb")
  MongodbAliValidationResult mgdbali(HttpServletRequest request) {
    try {
      MongoDatabase database = alimgdbValidationResult.MGDBClient().getDatabase("daivb");
      MongoCollection<Document> collection = database.getCollection("daivb");
      LocalDateTime oldDate = LocalDateTime.now();
      LOGGER.info("Ali集合选择成功");
      // Document document = new Document("fruit", "apple");
      // collection.insertOne(document);
      // LOGGER.info("文档插入成功");

      Document myDoc = collection.find().first();
      LOGGER.info((String) myDoc.get("fruit"));
      LOGGER.info("文档读取成功");
      LocalDateTime newDate = LocalDateTime.now();
      // count seconds between dates
      Duration duration = Duration.between(oldDate, newDate);
      this.alimgdbValidationResult.setResponseTime(duration.toMillis());
      this.alimgdbValidationResult.setAccessibility(true);
    } catch (Exception e) {
      this.alimgdbValidationResult.setAccessibility(false);
      LOGGER.error(e.getClass().getName() + ":[EXCEPtION] " + e.getMessage());
    }
    return this.alimgdbValidationResult;
  }
}
