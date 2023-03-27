package com.tradingp.frontend.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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

  @PostMapping("/order")
  public String order(@ModelAttribute Order order, Model model) {
    RestTemplate restTemplate = new RestTemplate();
    
    // request to backend
    String url = "http://localhost:8080/order"; 

    // response back from the backend
    ResponseEntity<Order> response = restTemplate.postForEntity(url, order, Order.class);

    model.addAttribute("order", response.getBody());

    return "redirect:/";

    
  }
  

}

