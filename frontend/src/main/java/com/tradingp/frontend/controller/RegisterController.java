package com.tradingp.frontend.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.client.RestTemplate;

import com.tradingp.frontend.models.User;

@Controller
@SessionAttributes("user")
public class RegisterController {

  @GetMapping("/register")
  public String register() {
    return "register";
  }

    // register method
    @PostMapping("/register")
    public String register(@ModelAttribute User loginFormData, Model model) {
      System.out.println(loginFormData);
      RestTemplate restTemplate = new RestTemplate();
      
      // request to backend
      String url = "http://localhost:8080/register";
  
      // response back from the backend
      ResponseEntity<User> response = restTemplate.postForEntity(url, loginFormData, User.class);
  
      if(response != null) {
        model.addAttribute("user", response);
        return "redirect:/register";
      } else {
        return "redirect:/";
      }
    }
  
}
