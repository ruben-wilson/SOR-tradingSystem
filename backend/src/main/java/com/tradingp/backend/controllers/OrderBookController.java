package com.tradingp.backend.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tradingp.backend.entities.OrderBook;
import com.tradingp.backend.services.orderBook.OrderBookRepoService;

@RestController
public class OrderBookController {
  
  @Autowired
  OrderBookRepoService orderBookRepoService;

  @CrossOrigin(origins = "http://localhost:5173")
  @GetMapping("/orderBook")
  public OrderBook findOrderBookByID(@RequestParam int id){
    OrderBook orderBook = orderBookRepoService.findItemById(id);
    
    System.out.println("\n ordrBookcontroller " + orderBook.getOrderList());
    return orderBook;
  }

}
