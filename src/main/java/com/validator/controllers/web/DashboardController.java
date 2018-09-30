package com.validator.controllers.web;

import com.cloudant.client.api.ClientBuilder;
import com.cloudant.client.api.CloudantClient;
import com.cloudant.client.api.Database;
import com.cloudant.client.api.views.Key;
import com.cloudant.client.api.views.ViewRequestBuilder;
import com.cloudant.client.api.views.ViewResponse;
import com.validator.beans.common.KeyValueBean;
import com.validator.beans.configuration.CloudantConfiguration;
import com.validator.beans.configuration.GlobalConfiguration;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DashboardController {

  private GlobalConfiguration globalConfiguration;
  private CloudantConfiguration cloudantConfiguration;

  public DashboardController(
      GlobalConfiguration statusConfiguration, CloudantConfiguration cloudantConfiguration) {
    this.globalConfiguration = statusConfiguration;
    this.cloudantConfiguration = cloudantConfiguration;
  }

  @GetMapping("/")
  public String home(Model model) throws IOException {

    CloudantClient client =
        ClientBuilder.account(this.cloudantConfiguration.getAccount())
            .username(this.cloudantConfiguration.getUsername())
            .password(this.cloudantConfiguration.getPassword())
            .build();

    Database db = client.database("dashboards", false);
    ViewRequestBuilder vrb = db.getViewRequestBuilder("dashboardViews", "dashboardView");
    ViewResponse<String, Object> response =
        vrb.newRequest(Key.Type.STRING, Object.class).descending(true).build().getResponse();

    List<KeyValueBean> list = new ArrayList<KeyValueBean>();

    for (ViewResponse.Row<String, Object> row : response.getRows()) {
      Object value = row.getValue();
      Object key = row.getKey();
      list.add(new KeyValueBean(key.toString(), value.toString()));
    }

    model.addAttribute("id", this.globalConfiguration.getId());
    model.addAttribute("location", this.globalConfiguration.getLocation());
    model.addAttribute("region", this.globalConfiguration.getRegion());
    model.addAttribute("dashboards", list);

    return "home";
  }
}
