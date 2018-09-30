package com.validator.controllers.api;

import com.validator.beans.ElasticValidationResult;
import com.validator.beans.configuration.ElasticConfigurationAliCloud;
import java.util.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

@RestController
@ComponentScan("com.validator.beans")
public class ElasticsearchController {
  private static Logger LOGGER = LoggerFactory.getLogger(ElasticsearchController.class);
  private final ElasticValidationResult validationResult;
  private final ElasticConfigurationAliCloud configurationAliCloud;
  private final RestTemplate template = new RestTemplate();

  public ElasticsearchController(
      ElasticValidationResult validationResult,
      ElasticConfigurationAliCloud configurationAliCloud) {
    this.validationResult = validationResult;
    this.configurationAliCloud = configurationAliCloud;

    System.out.println(validationResult.toString());
    System.out.println(configurationAliCloud);
  }

  @RequestMapping("/api/v1/ping/elasticsearch")
  public ElasticValidationResult pingAliElasticsearch() {
    String url = configurationAliCloud.getUrl();
    String username = configurationAliCloud.getUsername();
    String password = configurationAliCloud.getPassword();

    String userMsg = username + ":" + password;
    String base64UserMsg = Base64.getEncoder().encodeToString(userMsg.getBytes());

    HttpHeaders headers = new HttpHeaders();
    headers.add("Authorization ", "Basic " + base64UserMsg);

    HttpEntity<String> request = new HttpEntity<String>(headers);

    long begin = System.currentTimeMillis();
    ResponseEntity<String> response = null;
    try {
      response = template.exchange(url, HttpMethod.GET, request, String.class);
      String body = response.getBody();
      LOGGER.info("Elasticsearch indices:" + body);
    } catch (RestClientException e) {
      LOGGER.error("Ping elasticsearch fail." + e);
      validationResult.setResponseTime(System.currentTimeMillis() - begin);
      validationResult.setAccessibility(false);
      validationResult.setScalability(true);

      return validationResult;
    }
    if (response != null && response.getStatusCodeValue() != 200) {
      validationResult.setResponseTime(System.currentTimeMillis() - begin);
      validationResult.setAccessibility(false);
      validationResult.setScalability(true);
    } else {
      validationResult.setResponseTime(System.currentTimeMillis() - begin);
      validationResult.setAccessibility(true);
      validationResult.setScalability(true);
    }
    return validationResult;
  }
}
