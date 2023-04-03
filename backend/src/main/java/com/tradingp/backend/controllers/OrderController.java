package com.tradingp.backend.controllers;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.tradingp.backend.entities.Order;
import com.tradingp.backend.entities.OrderBook;
import com.tradingp.backend.services.OrderRepoService;
import com.tradingp.backend.services.orderBook.OrderBookRepoService;
import com.tradingp.backend.services.orderBook.OrderBookService;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
public class OrderController {
  
  @Autowired
  OrderRepoService orderRepoService;

  @Autowired
  OrderBookRepoService orderBookRepoService;

  @Autowired
  OrderBookService orderBookService;

  @CrossOrigin(origins = "http://localhost:5173")
  @PostMapping("/addOrder")
  public boolean addOrder(@RequestBody Order incomingOrder) {

      Order order = orderRepoService.createOrder(incomingOrder);
      OrderBook orderBook = orderBookRepoService.findItemById(1);
      order.setOrderBook(orderBook);

      Map<String, Object> orderBookResponse = orderBookService.matchOrder(order, orderBook);

      OrderBook updatedOrderBook = (OrderBook) orderBookResponse.get("orderBook");

      List<Order> executedTrades = (List<Order>) orderBookResponse.get("trades");

      System.out.println("\n order controller line 21: " + updatedOrderBook.getOrderList());
      System.out.println("\n order controller line 21: " + executedTrades);

      orderBook.setOrderList(updatedOrderBook.getOrderList());

      orderBookRepoService.addItem(orderBook);
      return true;
  }


  
}
