package com.example.cornellnote.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PageController {
  @GetMapping("/")
  public String index() {
    return "index";
  }

  @GetMapping("/login")
  public String login() {
    return "login";
  }

  @GetMapping("/signup")
  public String signup() {
    return "signup";
  }

  @GetMapping("/notes")
  public String notes() {
    return "notes";
  }

  @GetMapping("/editor")
  public String editor() {
    return "editor";
  }

  @GetMapping("/settings")
  public String settings() {
    return "settings";
  }

  @GetMapping("/share")
  public String share() {
    return "share";
  }
}
