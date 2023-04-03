package com.tradingp.backend.DTOs;

import java.util.List;

import com.tradingp.backend.entities.Order;

public class OrderBookOrdersDTO {

  private List<Order> orders;
  private int orderBookId;

  public OrderBookOrdersDTO() {
    super();
  }
  public List<Order> getOrders() {
    return orders;
  }
  public void setOrders(List<Order> orders) {
    this.orders = orders;
  }
  public int getOrderBookId() {
    return orderBookId;
  }
  public void setOrderBookId(int orderBookId) {
    this.orderBookId = orderBookId;
  }

  @Override
  public String toString() {
    return "OrderBookOrdersDTO [orders=" + orders + ", orderBookId=" + orderBookId + "]";
  }

  
}
