package com.tradingp.backend.entities;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class OrderBook {
  
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int orderBookId;

  @OneToMany(mappedBy = "orderBook", cascade = CascadeType.ALL)
  private List<Order> orderList;

  public OrderBook() {
    super();
  }

  public int getOrderBookId() {
    return orderBookId;
  }

  public void setOrderBookId(int orderBookId) {
    this.orderBookId = orderBookId;
  }

  public List<Order> getOrderList() {
    return orderList;
  }

  public void setOrderList(List<Order> orderList) {
    this.orderList = orderList;
  }

  public void addOrder(Order order){
    if (this.orderList == null) {
      this.orderList = new ArrayList<Order>();
    }

    this.orderList.add(order);
  }

  public Order findById(int orderId) {
    return  this.orderList.stream()
                          .filter(order -> order.getOrderId() == orderId)
                          .findFirst()
                          .orElse(null);
  }

  public boolean deleteOrder(Order orderToDelete) {
    if (this.orderList == null) {
      return false;
    }
  //  Order orderToRemove = this.orderList.stream()
  //                                      .filter(order -> order.getOrderId() == orderId)
  //                                      .findFirst()
  //                                      .orElse(null);  
  //  return orderToRemove != null ? false : this.orderList.remove(orderToRemove);   
   return this.orderList.remove(orderToDelete);                                
  }

  public void updateOrderQuantity(int orderId, double newQuantity) {
   
    int orderToUpdate = this.orderList.stream()
                            .filter(order -> order.getOrderId() == orderId)
                            .map(order ->  this.orderList.indexOf(order))
                            .findFirst()
                            .orElse(null);

    this.orderList.get(orderToUpdate).setQuantity(newQuantity);                   
  }
  

  public List<Order> getBids(){
    if(this.orderList == null){
      return null;
    }

    List<Order> allBids = orderList.stream()
                                   .filter(order -> order.isBid())
                                   .collect(Collectors.toList());  
    return allBids.isEmpty() ? null : allBids;    
  }

  public List<Order> getAsks() {
    if (this.orderList == null) {
      return null;
    }

    List<Order> allAsks =  orderList.stream()
                                    .filter(order -> !order.isBid())
                                    .collect(Collectors.toList());
    return allAsks.isEmpty() ? null : allAsks;
  }

  @Override
  public String toString() {
    return "OrderBook [orderBookId=" + orderBookId + ", orderList=" + null + "]";
  }


  
  



  
}
