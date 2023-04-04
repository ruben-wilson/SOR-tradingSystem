package com.tradingp.backend.controllers;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.tradingp.backend.entities.Order;
import com.tradingp.backend.entities.OrderBook;
import com.tradingp.backend.services.orderBook.OrderBookRepoService;

@RestController
public class OrderBookController {
  
  @Autowired
  OrderBookRepoService orderBookRepoService;

  @Autowired
  RestTemplate restTemplate;

  @CrossOrigin(origins = "http://localhost:5173")
  @GetMapping("/orderBook")
  public OrderBook findOrderBookByID(@RequestParam String symbol){
    OrderBook orderBook = orderBookRepoService.findOrderBookBySymbol(symbol);
    
    System.out.println("\n ordrBookcontroller " + orderBook.getOrderList());
    return orderBook;
  }

  @CrossOrigin(origins = "http://localhost:5173")
  @GetMapping("/liveOrderBook")
  public OrderBook loadLiveOrderBook(@RequestParam String symbol){

    String url = "https://api.bitfinex.com/v1/book/" + symbol + "?limit_asks=25&limit_bids=25";
    ResponseEntity<OrderBook> response = restTemplate.getForEntity(url, OrderBook.class);

    OrderBook responseOrderBook = response.getBody();

    OrderBook orderBook = orderBookRepoService.findOrderBookBySymbol(symbol);

    if(orderBook == null){

      responseOrderBook.setSymbol(symbol);
      orderBook = orderBookRepoService.saveLiveOrderBook(responseOrderBook);
    }else{

      List<Order> unmatchedInternalOrders = orderBook.getInternalOrders();
      orderBook.getOrderList().clear();
      orderBook.getOrderList().addAll(responseOrderBook.getOrderList());
      if(unmatchedInternalOrders != null){
        orderBook.getOrderList().addAll(unmatchedInternalOrders);
      }
      // orderBook.setOrderList(responseOrderBook.getOrderList());
      orderBook = orderBookRepoService.saveLiveOrderBook(orderBook);

    }

    return orderBook;
  }

}
