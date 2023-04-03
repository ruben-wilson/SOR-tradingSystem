package com.tradingp.backend.unitTests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;

import com.tradingp.backend.entities.Order;
import com.tradingp.backend.entities.OrderBook;
import com.tradingp.backend.services.CrossingEngine;
import com.tradingp.backend.services.orderBook.OrderBookService;

@ExtendWith(MockitoExtension.class)
public class OrderBookServiceTest {

  @Autowired
  OrderBookService orderBookService;

  @BeforeEach
  void createOrderBookService(){
    orderBookService = new OrderBookService();
  }

  @Test
  void zeroMatches_EmptyOrderPrice() {
    Order order = new Order(1, "bid", true, 0, 50, "unfulfilled", "12/3/21");

    OrderBook orderBook = new OrderBook();

    

    Map<String, Object> response = orderBookService.matchOrder(order, orderBook);

    assertNull(response);
  }

  @Test
  void zeroMatches_EmptyOrderQuantity() {
    Order order = new Order(1, "bid", true, 50, 0, "unfulfilled", "12/3/21");

    OrderBook orderBook = new OrderBook();

    

    Map<String, Object> response = orderBookService.matchOrder(order, orderBook);

    assertNull(response);
  }

  @Test
  void sets_bidsAndAsks_WhenNull() {
    OrderBook orderBook = new OrderBook();

    assertEquals(true, orderBookService.setBidsAndAsks(orderBook));
  }
  
  @Test
  void sets_bidsAndAsks_WhenNotNull() {
    Order order = new Order(1, "bid", true, 100, 50, "unfulfilled", "12/3/21");
    OrderBook orderBook = new OrderBook();
    orderBook.addOrder(order);

    assertEquals(false, orderBookService.setBidsAndAsks(orderBook));
  }


  @Test
  void zeroMatches_EmptyOrderBook() {
    Order order = new Order(1, "bid", true, 100, 50, "unfulfilled", "12/3/21");

    OrderBook orderBook = new OrderBook();

    Map<String, Object> response = orderBookService.matchOrder(order, orderBook);

    OrderBook outputOrderBook = (OrderBook) response.get("orderBook");

    assertNotNull(outputOrderBook);
    List<Order> actualBidOutput = outputOrderBook.getBids();

    List<Order> expectedBidOutput = Arrays.asList(order);

    assertNull(response.get("order"));
    assertEquals(1, outputOrderBook.getBids().size());
    assertEquals(expectedBidOutput, actualBidOutput);
  }

  @Test
  void zeroMatches_OrderBook_NoAsks() {
    Order bidOrder = new Order(1, "bid", true, 100, 50, "unfulfilled", "12/3/21");

    OrderBook orderBook = new OrderBook();
    orderBook.addOrder(bidOrder);

    Map<String, Object> response = orderBookService.matchOrder(bidOrder, orderBook);

    Order outputOrder = (Order) response.get("order");
    OrderBook outputOrderBook = (OrderBook) response.get("orderBook");

    assertNull(outputOrder);

    assertNotNull(outputOrderBook);
    assertEquals(2, outputOrderBook.getOrderList().size());
    assertEquals(2, outputOrderBook.getBids().size());
  }

  @Test
  void orderBook_findLimitAsk(){
    Order order1 = new Order(1, "bids", true, 100, 50, "unfulfilled", "12/3/21");
    Order order2 = new Order(2, "bids", true, 100, 50, "unfulfilled", "12/3/21");
    Order order3 = new Order(3, "bids", true, 80, 5000, "unfulfilled", "12/3/21");
    Order order4 = new Order(4, "bids", true, 101, 50, "unfulfilled", "12/3/21");
    List<Order> openPositions = Arrays.asList(order1, order2, order3, order4);

    Order incomingOrder = new Order(1, "ask", false, 100, 50, "unfulfilled", "12/3/21");

    List<Order> positionsAtLimit = orderBookService.findLimit(incomingOrder, openPositions);
    assertEquals(3, positionsAtLimit.size());
  }

  @Test
  void orderBook_findLimitBid(){
    Order order1 = new Order(1, "asks", false, 100, 50, "unfulfilled", "12/3/21");
    Order order2 = new Order(2, "asks", false, 100, 50, "unfulfilled", "12/3/21");
    Order order3 = new Order(3, "asks", false, 80, 5000, "unfulfilled", "12/3/21");
    Order order4 = new Order(4, "asks", false, 101, 50, "unfulfilled", "12/3/21");
    List<Order> openPositions = Arrays.asList(order1, order2, order3, order4);

    Order incomingOrder = new Order(1, "bid", true, 100, 50, "unfulfilled", "12/3/21");

    List<Order> positionsAtLimit = orderBookService.findLimit(incomingOrder, openPositions);
    assertEquals(3, positionsAtLimit.size());
  }

  @Test
  void orderBook_findLimitBid_noMatches() {
    Order order1 = new Order(1, "asks", false, 100, 50, "unfulfilled", "12/3/21");
    Order order2 = new Order(2, "asks", false, 100, 50, "unfulfilled", "12/3/21");
    Order order3 = new Order(3, "asks", false, 80.001, 5000, "unfulfilled", "12/3/21");
    Order order4 = new Order(4, "asks", false, 101, 50, "unfulfilled", "12/3/21");
    List<Order> openPositions = Arrays.asList(order1, order2, order3, order4);

    Order incomingOrder = new Order(1, "bid", true, 80, 50, "unfulfilled", "12/3/21");

    List<Order> positionsAtLimit = orderBookService.findLimit(incomingOrder, openPositions);
    assertEquals(null, positionsAtLimit);
  }

  @Test
  void orderBook_findLimitAsk_noMatches() {
    Order order1 = new Order(1, "bids", true, 100, 50, "unfulfilled", "12/3/21");
    Order order2 = new Order(2, "bids", true, 100, 50, "unfulfilled", "12/3/21");
    Order order3 = new Order(3, "bids", true, 80, 5000, "unfulfilled", "12/3/21");
    Order order4 = new Order(4, "bids", true, 101, 50, "unfulfilled", "12/3/21");
    List<Order> openPositions = Arrays.asList(order1, order2, order3, order4);

    Order incomingOrder = new Order(1, "ask", false, 102, 50, "unfulfilled", "12/3/21");

    List<Order> positionsAtLimit = orderBookService.findLimit(incomingOrder, openPositions);
    assertEquals(null, positionsAtLimit);
  }

  @Test
  void executesTrade_Bid_twoFulfilled(){
    Order order1 = new Order(1, "asks", false, 100, 50, "unfulfilled", "12/3/21");
    Order order2 = new Order(2, "asks", false, 100, 50, "unfulfilled", "12/3/21");
    Order order3 = new Order(3, "asks", false, 100, 5000, "unfulfilled", "12/3/21");
    Order order4 = new Order(4, "asks", false, 100, 50, "unfulfilled", "12/3/21");
    List<Order> ordersAtLimit = Arrays.asList(order1, order2, order3, order4);

    Order incomingOrder = new Order(7, "bid", true, 102, 50, "unfulfilled", "12/3/21");

    List<Order> executedTrades = orderBookService.executeTrades(ordersAtLimit, incomingOrder);

    assertEquals(2, executedTrades.size());
    assertEquals(7, executedTrades.get(0).getOrderId());
    assertEquals(1, executedTrades.get(1).getOrderId());
  }

  @Test
  void executesTrade_ask_twoFulfilled() {
    Order order1 = new Order(1, "bids", true, 100, 50, "unfulfilled", "12/3/21");
    Order order2 = new Order(2, "bids", true, 100, 50, "unfulfilled", "12/3/21");
    Order order3 = new Order(3, "bids", true, 100, 5000, "unfulfilled", "12/3/21");
    Order order4 = new Order(4, "bids", true, 100, 50, "unfulfilled", "12/3/21");
    List<Order> ordersAtLimit = Arrays.asList(order1, order2, order3, order4);

    Order incomingOrder = new Order(7, "ask", false, 102, 50, "unfulfilled", "12/3/21");

    List<Order> executedTrades = orderBookService.executeTrades(ordersAtLimit, incomingOrder);

    assertEquals(2, executedTrades.size());
    assertEquals(7, executedTrades.get(0).getOrderId());
    assertEquals(1, executedTrades.get(1).getOrderId());
  }

  @Test
  void executesTrade_Bid_MultiFulfilled() {
    Order order1 = new Order(1, "asks", false, 100, 50, "unfulfilled", "12/3/21");
    Order order2 = new Order(2, "asks", false, 100, 50, "unfulfilled", "12/3/21");
    Order order3 = new Order(3, "asks", false, 100, 5000, "unfulfilled", "12/3/21");
    Order order4 = new Order(4, "asks", false, 100, 50, "unfulfilled", "12/3/21");
    List<Order> ordersAtLimit = Arrays.asList(order1, order2, order3, order4);

    Order incomingOrder = new Order(7, "bid", true, 100, 100, "unfulfilled", "12/3/21");

    List<Order> executedTrades = orderBookService.executeTrades(ordersAtLimit, incomingOrder);

    assertEquals(3, executedTrades.size());
    assertEquals(1, executedTrades.get(0).getOrderId());
    assertEquals(7, executedTrades.get(1).getOrderId());
    assertEquals(2, executedTrades.get(2).getOrderId());
  }

  @Test
  void executesTrade_bid_MultiFulfilled() {
    Order order1 = new Order(1, "asks", false, 100, 50, "unfulfilled", "12/3/21");
    Order order2 = new Order(2, "asks", false, 100, 50, "unfulfilled", "12/3/21");
    Order order3 = new Order(3, "asks", false, 80.001, 80, "unfulfilled", "12/3/21");
    Order order4 = new Order(4, "asks", false, 101, 50, "unfulfilled", "12/3/21");
    List<Order> ordersAtLimit = Arrays.asList(order1, order2, order3, order4);

    Order incomingOrder = new Order(7, "bid", true, 80, 140.0, "unfulfilled", "12/3/21");

    List<Order> executedTrades = orderBookService.executeTrades(ordersAtLimit, incomingOrder);

    assertEquals(4, executedTrades.size());
    assertEquals(1, executedTrades.get(0).getOrderId());
    assertEquals(2, executedTrades.get(1).getOrderId());
    assertEquals(3, executedTrades.get(2).getOrderId());
    assertEquals(40, executedTrades.get(2).getQuantity());
    assertEquals(7, executedTrades.get(3).getOrderId());
  }


  @Test
  void OrderBook_OneMatch_bidWithAskOne() {
    Order bidOrder = new Order(1, "bid", true, 100, 50, "unfulfilled", "12/3/21");
    Order askOrder = new Order(2, "ask", false, 100, 50, "unfulfilled", "12/3/21");

    OrderBook orderBook = new OrderBook();
    orderBook.addOrder(askOrder);

    Map<String, Object> response = orderBookService.matchOrder(bidOrder, orderBook);

    List<Order> trades = (List<Order>) response.get("trades");
    OrderBook outputOrderBook = (OrderBook) response.get("orderBook");

    
    assertNotNull(outputOrderBook);
    assertEquals(true, outputOrderBook.getOrderList().isEmpty());
    assertEquals(null, outputOrderBook.getBids());
    assertEquals(2, trades.size());
    assertEquals(1, trades.get(0).getOrderId());
    assertEquals(2, trades.get(1).getOrderId());
    assertEquals(0, trades.get(0).getQuantity());
    assertEquals(0, trades.get(1).getQuantity());
  }

  @Test
  void OrderBook_NoMatch_bidWithAsk() {
    Order bidOrder = new Order(1, "bid", true, 100, 50, "unfulfilled", "12/3/21");
    Order askOrder = new Order(2, "ask", false, 100.001, 50, "unfulfilled", "12/3/21");

    OrderBook orderBook = new OrderBook();
    orderBook.addOrder(askOrder);

    Map<String, Object> response = orderBookService.matchOrder(bidOrder, orderBook);

    List<Order> trades = (List<Order>) response.get("trades");
    OrderBook outputOrderBook = (OrderBook) response.get("orderBook");

    assertNotNull(outputOrderBook);
    assertEquals(false, outputOrderBook.getOrderList().isEmpty());
    assertEquals(1, outputOrderBook.getBids().size());
    assertEquals(1, outputOrderBook.getAsks().size());
    assertEquals(1, outputOrderBook.getBids().get(0).getOrderId());
    assertEquals(2, outputOrderBook.getAsks().get(0).getOrderId());

    assertNull(trades);
  }

  @Test
  void OrderBook_4Match_bidWithAsk() {
    Order bidOrder = new Order(1, "bid", true, 102, 200, "unfulfilled", "12/3/21");
   
    Order askOrder1 = new Order(2, "ask", false, 100.001, 50, "unfulfilled", "12/3/21");
    Order askOrder2 = new Order(3, "ask", false, 100.001, 50, "unfulfilled", "12/3/21");
    Order askOrder3 = new Order(4, "ask", false, 100.001, 50, "unfulfilled", "12/3/21");
    Order askOrder4 = new Order(5, "ask", false, 100.001, 50, "unfulfilled", "12/3/21");
    Order askOrder5 = new Order(6, "ask", false, 102.001, 50, "unfulfilled", "12/3/21");

    OrderBook orderBook = new OrderBook();
    orderBook.addOrder(askOrder1);
    orderBook.addOrder(askOrder2);
    orderBook.addOrder(askOrder3);
    orderBook.addOrder(askOrder4);
    orderBook.addOrder(askOrder5);

    Map<String, Object> response = orderBookService.matchOrder(bidOrder, orderBook);

    List<Order> trades = (List<Order>) response.get("trades");
    OrderBook outputOrderBook = (OrderBook) response.get("orderBook");

    assertNotNull(outputOrderBook);
    assertEquals(false, outputOrderBook.getOrderList().isEmpty());
    assertEquals(1, outputOrderBook.getOrderList().size());
    assertEquals(null, outputOrderBook.getBids());
    assertEquals(6, outputOrderBook.getAsks().get(0).getOrderId());

    assertEquals(5, trades.size());
  }
  
  @Test
  void OrderBook_1Match_withpartiallyFilled() {
    Order bidOrder = new Order(1, "bid", true, 102, 150, "unfulfilled", "12/3/21");

    Order askOrder1 = new Order(2, "ask", false, 100.001, 100, "unfulfilled", "12/3/21");
    Order askOrder2 = new Order(3, "ask", false, 100.001, 100, "unfulfilled", "12/3/21");

    OrderBook orderBook = new OrderBook();
    orderBook.addOrder(askOrder1);
    orderBook.addOrder(askOrder2);


    Map<String, Object> response = orderBookService.matchOrder(bidOrder, orderBook);

    List<Order> trades = (List<Order>) response.get("trades");
    OrderBook outputOrderBook = (OrderBook) response.get("orderBook");

    assertNotNull(outputOrderBook);
    assertEquals(false, outputOrderBook.getOrderList().isEmpty());
    assertEquals(1, outputOrderBook.getOrderList().size());
    assertEquals(3, outputOrderBook.getAsks().get(0).getOrderId());
    assertEquals(50, outputOrderBook.getAsks().get(0).getQuantity());

    assertEquals(3, trades.size());
    assertEquals(50, trades.get(1).getQuantity());
  }

  @Test
  void OrderBook_OneMatch_askWithBidOne() {
    Order bidOrder = new Order(1, "bid", true, 100, 50, "unfulfilled", "12/3/21");
    Order askOrder = new Order(2, "ask", false, 100, 50, "unfulfilled", "12/3/21");

    OrderBook orderBook = new OrderBook();
    orderBook.addOrder(bidOrder);

    Map<String, Object> response = orderBookService.matchOrder(askOrder, orderBook);

    List<Order> trades = (List<Order>) response.get("trades");
    OrderBook outputOrderBook = (OrderBook) response.get("orderBook");

    assertNotNull(outputOrderBook);
    assertEquals(true, outputOrderBook.getOrderList().isEmpty());
    
    assertEquals(2, trades.size());
    assertEquals(2, trades.get(0).getOrderId());
    assertEquals(1, trades.get(1).getOrderId());
    assertEquals(0, trades.get(0).getQuantity());
    assertEquals(0, trades.get(1).getQuantity());
  }

  @Test
  void OrderBook_4Match_askWithBidOne() {
    Order askOrder = new Order(5, "ask", false, 90, 200, "unfulfilled", "12/3/21");

    Order order1 = new Order(1, "bids", true, 100, 50, "unfulfilled", "12/3/21");
    Order order2 = new Order(2, "bids", true, 100, 50, "unfulfilled", "12/3/21");
    Order order3 = new Order(3, "bids", true, 80, 50, "unfulfilled", "12/3/21");
    Order order4 = new Order(4, "bids", true, 101, 50, "unfulfilled", "12/3/21");
    Order order5 = new Order(5, "bids", true, 101, 50, "unfulfilled", "12/3/21");

    OrderBook orderBook = new OrderBook();
    orderBook.addOrder(order1);
    orderBook.addOrder(order2);
    orderBook.addOrder(order3);
    orderBook.addOrder(order4);
    orderBook.addOrder(order5);

    Map<String, Object> response = orderBookService.matchOrder(askOrder, orderBook);

    List<Order> trades = (List<Order>) response.get("trades");
    OrderBook outputOrderBook = (OrderBook) response.get("orderBook");

    assertNotNull(outputOrderBook);
    assertEquals(false, outputOrderBook.getOrderList().isEmpty());
    assertEquals(1, outputOrderBook.getOrderList().size());
    assertEquals(null, outputOrderBook.getAsks());
    assertEquals(3, outputOrderBook.getBids().get(0).getOrderId());

    assertEquals(5, trades.size());
  }

  // @Test
  // void OrderBook_1Match_askWithBid_excessQuantity() {
  //   Order askOrder = new Order(5, "ask", false, 90, 200, "unfulfilled", "12/3/21");

   
  //   Order order1 = new Order(3, "bids", true, 100, 50, "unfulfilled", "12/3/21");


  //   OrderBook orderBook = new OrderBook();
  //   orderBook.addOrder(order1);


  //   Map<String, Object> response = orderBookService.matchOrder(askOrder, orderBook);

  //   List<Order> trades = (List<Order>) response.get("trades");
  //   OrderBook outputOrderBook = (OrderBook) response.get("orderBook");

  //   assertNotNull(outputOrderBook);
  //   assertEquals(2, trades.get(0).getOrderId());
  //   assertEquals(2, trades.size());
    

  //   assertEquals(true, outputOrderBook.getOrderList().isEmpty());
  //   assertEquals(0, outputOrderBook.getOrderList().size());
  //   assertEquals(null, outputOrderBook.getAsks());
  //   assertEquals(3, outputOrderBook.getBids().get(0).getOrderId());

   
  // }


  


}
