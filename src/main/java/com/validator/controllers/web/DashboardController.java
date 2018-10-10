package com.validator.controllers.web;

import com.validator.beans.configuration.GlobalConfiguration;
import java.io.IOException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DashboardController {

  private GlobalConfiguration globalConfiguration;

  public DashboardController(GlobalConfiguration statusConfiguration) {
    this.globalConfiguration = statusConfiguration;
  }

  @GetMapping("/")
  public String home(Model model) throws IOException {

    model.addAttribute("id", this.globalConfiguration.getId());
    model.addAttribute("location", this.globalConfiguration.getLocation());
    model.addAttribute("region", this.globalConfiguration.getRegion());

    return "home";
  }
}
