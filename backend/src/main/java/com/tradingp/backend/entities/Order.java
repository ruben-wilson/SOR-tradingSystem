package com.tradingp.backend.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "orders")
public class Order {
  
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private int id;

  @Column(name = "order_type")
  private String orderType;

  @Column(name = "price")
  private double price;

  @Column(name = "quantity")
  private int quantity;

  @Column(name = "status")
  private String status;

  @Column(name = "date")
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
