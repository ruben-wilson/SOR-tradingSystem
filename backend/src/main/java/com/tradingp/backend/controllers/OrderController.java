package com.tradingp.backend.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.tradingp.backend.entities.Order;
import com.tradingp.backend.entities.OrderBook;
import com.tradingp.backend.services.OrderRepoService;
import com.tradingp.backend.services.orderBook.OrderBookRepoService;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
public class OrderController {
  
  @Autowired
  OrderRepoService orderRepoService;

  @Autowired
  OrderBookRepoService orderBookRepoService;

  @CrossOrigin(origins = "http://localhost:5173")
  @PostMapping("/addOrder")
  public boolean addOrder(@RequestBody Order order) {

      System.out.println("order controller line 21: " + order);

      OrderBook orderBook = orderBookRepoService.findItemById(1);
      
      order.setOrderBook(orderBook);

      Order response = orderRepoService.addItem(order);
      return response != null ? true : false;
  }


  
}
