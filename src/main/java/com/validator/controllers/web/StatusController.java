package com.validator.controllers.web;

import java.io.IOException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class StatusController {

  @GetMapping("/")
  public String home(
      @RequestParam(name = "name", required = false, defaultValue = "World") String name,
      Model model)
      throws IOException {

    model.addAttribute("name", name);
    model.addAttribute("bean", "World");

    return "home";
  }
}
