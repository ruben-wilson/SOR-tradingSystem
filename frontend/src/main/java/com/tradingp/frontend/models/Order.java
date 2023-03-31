package com.tradingp.frontend.models;

public class Order {
  
  private int id;

  private String orderType;

  private double price;

  private int quantity;

  private String status;

  private String date;

  // Getters and Setters

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
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

  public int getQuantity() {
    return quantity;
  }

  public void setQuantity(int quantity) {
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

  // toString()

  @Override
  public String toString() {
    return "Order [date=" + date + ", id=" + id + ", orderType=" + orderType + ", price=" + price + ", quantity="
        + quantity + ", status=" + status + "]";
  }

  // Constructors

  public Order() {
  }

  public Order(int id, String orderType, double price, int quantity, String status, String date) {
    this.id = id;
    this.orderType = orderType;
    this.price = price;
    this.quantity = quantity;
    this.status = status;
    this.date = date;
  }


}
