package com.tradingp.backend.controllers;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.tradingp.backend.entities.Order;
import com.tradingp.backend.services.OrderService;

@RestController
public class OrderController {

  @Autowired
  OrderService orderService;

  @GetMapping("/order")
  public String newOrders() {
    return "order";
  }

  @GetMapping("/neworderfailure")
  public String newOrderFailure() {
    return "neworderfailure";
  }

  @PostMapping("/order")
  public ResponseEntity<Map<String, String>> newOrder(@RequestBody Order order) {

    LocalDateTime dateTime = LocalDateTime.now();
		String formattedDate = dateTime.format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss"));

    Order o = new Order();

    o.setDate(formattedDate);

    Order savedInfo = orderService.addOrder(o);

    Map<String, String> response = new HashMap<>();


    if (savedInfo != null) {
      response.put("status", "success");
      response.put("message", "Order created successfully with ID: " + savedInfo.getOrderId());
      return ResponseEntity.ok(response);
  } else {
      response.put("status", "error");
      response.put("message", "Failed to create order");
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
  }

}

}
