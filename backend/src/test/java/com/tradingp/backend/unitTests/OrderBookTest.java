package com.tradingp.backend.unitTests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;

import com.tradingp.backend.entities.Order;
import com.tradingp.backend.entities.OrderBook;

@ExtendWith(MockitoExtension.class)
public class OrderBookTest {

  @Autowired
  OrderBook orderBook;
  
  @BeforeEach
  void createOrderBook() {
    orderBook = new OrderBook();
  }

  @Test
  void returnsBids() {
    Order order1 = new Order(1, "bid", true, 0, 50, "unfulfilled", "12/3/21");
    Order order2 = new Order(2, "bid", true, 0, 50, "unfulfilled", "12/3/21");
    Order order3 = new Order(3, "bid", true, 0, 50, "unfulfilled", "12/3/21");
    Order order4 = new Order(3, "ask", false, 0, 50, "unfulfilled", "12/3/21");

    List<Order> orderList = Arrays.asList(order1, order2, order3, order4);
    
    orderBook.setOrderList(orderList);

     List<Order> outputBids = orderBook.getBids();

    assertEquals(3, outputBids.size());
  }

  @Test
  void returnsBids_whenBidsAreNull() {
    Order order1 = new Order(1, "ask", false, 0, 50, "unfulfilled", "12/3/21");
    Order order2 = new Order(2, "ask", false, 0, 50, "unfulfilled", "12/3/21");
    Order order3 = new Order(3, "ask", false, 0, 50, "unfulfilled", "12/3/21");

    List<Order> orderList = Arrays.asList(order1, order2, order3);

    orderBook.setOrderList(orderList);

    List<Order> outputBids = orderBook.getBids();

    assertNull(outputBids);
  }

  @Test
  void returnsAsks() {
    Order order1 = new Order(1, "ask", false, 0, 50, "unfulfilled", "12/3/21");
    Order order2 = new Order(2, "ask", false, 0, 50, "unfulfilled", "12/3/21");
    Order order3 = new Order(3, "ask", false, 0, 50, "unfulfilled", "12/3/21");
    Order order4 = new Order(3, "bid", true, 0, 50, "unfulfilled", "12/3/21");

    List<Order> orderList = Arrays.asList(order1, order2, order3, order4);

    orderBook.setOrderList(orderList);

    List<Order> outputBids = orderBook.getAsks();

    assertEquals(3, outputBids.size());
  }

  @Test
  void returnsAsks_whenAsksAreNull() {
    Order order1 = new Order(1, "bids", true, 0, 50, "unfulfilled", "12/3/21");
    Order order2 = new Order(2, "bids", true, 0, 50, "unfulfilled", "12/3/21");
    Order order3 = new Order(3, "bids", true, 0, 50, "unfulfilled", "12/3/21");

    List<Order> orderList = Arrays.asList(order1, order2, order3);

    orderBook.setOrderList(orderList);

    List<Order> outputAsks = orderBook.getAsks();

    assertNull(outputAsks);
  }

  @Test
  void getBids_getAsks_returnsNull_WhenOrderListNull() {



    assertNull(orderBook.getAsks());
    assertNull(orderBook.getBids());
  }

  @Test
  void addOrder() {
    Order order1 = new Order(1, "bids", true, 0, 50, "unfulfilled", "12/3/21");
    Order order2 = new Order(2, "bids", true, 0, 50, "unfulfilled", "12/3/21");
    Order order3 = new Order(3, "bids", true, 0, 50, "unfulfilled", "12/3/21");

    orderBook.addOrder(order1);
    orderBook.addOrder(order2);
    orderBook.addOrder(order3);
    
    List<Order> outputBids = orderBook.getBids();

    assertEquals(3, outputBids.size());
  }
  
  @Test
  void deleteOrder_OneOrder(){
    Order order = new Order(1, "bids", true, 100, 50, "unfulfilled", "12/3/21");
    orderBook.addOrder(order);

    assertEquals(1, orderBook.getOrderList().size());
    assertEquals(1, orderBook.getBids().size());
    Order outputOrder = orderBook.getBids().get(0);
    assertEquals(100, outputOrder.getPrice());
    assertEquals(1, outputOrder.getOrderId());

    assertEquals(true, orderBook.deleteOrder(order));
    orderBook.deleteOrder(order);
    assertEquals(0, orderBook.getOrderList().size());
    assertEquals(null, orderBook.getBids());
    assertEquals(false, orderBook.deleteOrder(order));
  }

  @Test
  void deleteOrder_nullOrder() {
    Order order = new Order(1, "bids", true, 100, 50, "unfulfilled", "12/3/21");
    orderBook.addOrder(order);

    assertEquals(1, orderBook.getOrderList().size());
    assertEquals(1, orderBook.getBids().size());
    Order outputOrder = orderBook.getBids().get(0);
    assertEquals(100, outputOrder.getPrice());
    assertEquals(1, outputOrder.getOrderId());

    assertEquals(false, orderBook.deleteOrder(null));
    assertEquals(1, orderBook.getOrderList().size());
    assertEquals(1, orderBook.getBids().size());

    orderBook.deleteOrder(order);
    assertEquals(0, orderBook.getOrderList().size());
    assertEquals(null, orderBook.getBids());
    assertEquals(false, orderBook.deleteOrder(order));
  }

  @Test
  void deleteOrder_MultiOrdersInBook() {
    Order order1 = new Order(1, "bids", true, 100, 50, "unfulfilled", "12/3/21");
    Order order2 = new Order(2, "bids", true, 100, 50, "unfulfilled", "12/3/21");
    Order order3 = new Order(3, "bids", true, 100, 50, "unfulfilled", "12/3/21");
    Order order4 = new Order(4, "bids", true, 100, 50, "unfulfilled", "12/3/21");
    orderBook.addOrder(order1);
    orderBook.addOrder(order2);
    orderBook.addOrder(order3);
    orderBook.addOrder(order4);

    assertEquals(4, orderBook.getOrderList().size());

    assertEquals(true, orderBook.deleteOrder(order3));
    orderBook.deleteOrder(order3);
    assertEquals(3, orderBook.getOrderList().size());
    assertEquals(3, orderBook.getBids().size());
    assertEquals(false, orderBook.deleteOrder(order3));
  }

  @Test
  void updatesElement(){
    Order order1 = new Order(1, "bids", true, 100, 50, "unfulfilled", "12/3/21");
    Order order2 = new Order(2, "bids", true, 100, 50, "unfulfilled", "12/3/21");
    Order order3 = new Order(3, "bids", true, 100, 50, "unfulfilled", "12/3/21");
    Order order4 = new Order(4, "bids", true, 100, 50, "unfulfilled", "12/3/21");
    orderBook.addOrder(order1);
    orderBook.addOrder(order2);
    orderBook.addOrder(order3);
    orderBook.addOrder(order4);

    assertEquals(4, orderBook.getOrderList().size());

    orderBook.updateOrderQuantity(order3.getOrderId(), 50);
    assertEquals(50, orderBook.getOrderList().get(3).getQuantity());

  }

  @Test
  void findsElement() {
    Order order1 = new Order(1, "bids", true, 100, 50, "unfulfilled", "12/3/21");
    Order order2 = new Order(2, "bids", true, 100, 50, "unfulfilled", "12/3/21");
    Order order3 = new Order(3, "bids", true, 80, 5000, "unfulfilled", "12/3/21");
    Order order4 = new Order(4, "bids", true, 100, 50, "unfulfilled", "12/3/21");
    orderBook.addOrder(order1);
    orderBook.addOrder(order2);
    orderBook.addOrder(order3);
    orderBook.addOrder(order4);

    assertEquals(4, orderBook.getOrderList().size());

    Order outputOrder = orderBook.findById(order3.getOrderId());

    assertEquals(3, outputOrder.getOrderId());
    assertEquals(5000, outputOrder.getQuantity());
    assertEquals(80, outputOrder.getPrice());

  }

}
