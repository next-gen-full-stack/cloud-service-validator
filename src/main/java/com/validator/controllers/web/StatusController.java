package com.validator.controllers.web;

import com.validator.beans.configuration.StatusConfiguration;
import java.io.IOException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class StatusController {

  private StatusConfiguration statusConfiguration;

  public StatusController(StatusConfiguration statusConfiguration) {
    this.statusConfiguration = statusConfiguration;
  }

  @GetMapping("/")
  public String home(Model model) throws IOException {

    model.addAttribute("location", this.statusConfiguration.getLocation());
    model.addAttribute("region", this.statusConfiguration.getRegion());

    return "home";
  }
}
