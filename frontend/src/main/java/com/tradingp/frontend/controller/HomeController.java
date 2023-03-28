package com.tradingp.frontend.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.client.RestTemplate;

import com.tradingp.frontend.models.Order;

@Controller
public class HomeController {
  
  @GetMapping("/")
  public String home() {
    return "home";
  }

  @GetMapping("/login")
  public String login() {
    return "login";
  }

  @GetMapping("/register")
  public String register() {
    return "register";
  }

  @GetMapping("/neworder")
  public String newOrder() {
    return "neworder";
  }

  @PostMapping("/order")
  public String order(@ModelAttribute Order order) {
    System.out.println(order);
    RestTemplate restTemplate = new RestTemplate();
    
    // request to backend
    String url = "http://localhost:8080/order";

    // response back from the backend
    ResponseEntity<Order> response = restTemplate.postForEntity(url, order, Order.class);

    return "redirect:/";
  }
}

