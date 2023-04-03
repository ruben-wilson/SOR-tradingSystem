package com.tradingp.frontend.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.client.RestTemplate;

import com.tradingp.frontend.models.User;

@Controller
public class HomeController {

  @ModelAttribute("user")
  public User user() {
    return new User();
  }
  
  @GetMapping("/")
  public String home() {
    return "home";
  }

  @GetMapping("/login")
  public String login() {
    return "login";
  }

  // login method
  @PostMapping("/login")
  public String login(@ModelAttribute User user, Model model) {
    System.out.println(user);
    RestTemplate restTemplate = new RestTemplate();
    
    // request to backend
    String url = "http://localhost:8080/login";

    // response back from the backend
    User response = restTemplate.postForObject(url, user, User.class);

    if(response != null) {
      model.addAttribute("user", response);
      return "redirect:/login";
    } else {
      return "redirect:/";
    }
  }
}

