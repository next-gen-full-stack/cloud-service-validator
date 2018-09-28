package com.validator.controllers.api;

import com.cloudant.client.api.ClientBuilder;
import com.cloudant.client.api.CloudantClient;
import com.cloudant.client.api.Database;
import com.cloudant.client.api.views.Key;
import com.cloudant.client.api.views.ViewRequestBuilder;
import com.cloudant.client.api.views.ViewResponse;
import com.validator.beans.configuration.CloudantConfiguration;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ConfigurationController {

  private CloudantConfiguration cloudantConfiguration;

  public ConfigurationController(CloudantConfiguration cloudantConfiguration) {

    this.cloudantConfiguration = cloudantConfiguration;
  }

  @RequestMapping("/js/config.js")
  public List<Object> getCloudProviders() throws IOException {

    CloudantClient client =
        ClientBuilder.account(this.cloudantConfiguration.getAccount())
            .username(this.cloudantConfiguration.getUsername())
            .password(this.cloudantConfiguration.getPassword())
            .build();

    Database db = client.database("providers", false);
    ViewRequestBuilder vrb = db.getViewRequestBuilder("providerViews", "cloudProvider");
    ViewResponse<String, Object> response =
        vrb.newRequest(Key.Type.STRING, Object.class).descending(true).build().getResponse();

    List<Object> list = new ArrayList<Object>();

    for (ViewResponse.Row<String, Object> row : response.getRows()) {
      Object value = row.getValue();
      list.add(value);
    }
    return list;
  }
}
