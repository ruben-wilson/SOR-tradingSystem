package com.tradingp.backend.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Order {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int orderId;

  private String orderType;

  private boolean isBid;

  private double price;

  @ManyToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "order_book_id")
  @JsonIgnore
  private OrderBook orderBook;

  private double initialQuantity;
  
  private double quantity;

  private String status;

  private String date;


  public Order() {
    super();
  }


  public Order(int orderId, String orderType, boolean isBid, double price, double quantity, String status,
      String date) {
    this.orderId = orderId;
    this.orderType = orderType;
    this.isBid = isBid;
    this.price = price;
    this.quantity = quantity;
    this.status = status;
    this.date = date;
  }

  public int getOrderId() {
    return orderId;
  }

  public void setOrderId(int orderId) {
    this.orderId = orderId;
  }

  public String getOrderType() {
    return orderType;
  }

  public void setOrderType(String orderType) {
    this.orderType = orderType;
  }

  public double getPrice() {
    return price;
  }

  public void setPrice(double price) {
    this.price = price;
  }

  public double getQuantity() {
    return quantity;
  }

  public void setQuantity(double quantity) {
    this.quantity = quantity;
  }

  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }

  public String getDate() {
    return date;
  }
  

  public void setDate(String date) {
    this.date = date;
  }

  public boolean isBid() {
    return this.isBid;
  }

  public void setBid(boolean isBid) {
    this.isBid = isBid;
  }

  public boolean getIsBid() {
    return this.isBid;
  }

  public void setIsBid(boolean isBid) {
    this.isBid = isBid;
  }

  public OrderBook getOrderBook() {
    return orderBook;
  }

  public void setOrderBook(OrderBook orderBook) {
    this.orderBook = orderBook;
  }

  public double getInitialQuantity() {
    return initialQuantity;
  }

  public void setInitialQuantity(double initialQuantity) {
    this.initialQuantity = initialQuantity;
  }

  
  @Override
  public String toString() {
    return "Order [orderId=" + orderId + ", orderType=" + orderType + ", isBid=" + isBid + ", price=" + price
        + ", orderBook=" + orderBook + ", quantity=" + quantity + ", status=" + status + ", date=" + date + "]";
  }

  
  

}
