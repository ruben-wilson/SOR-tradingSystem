package com.tradingp.backend.services;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tradingp.backend.entities.Order;
import com.tradingp.backend.repos.OrderRepository;

@Service
public class OrderRepoService implements repoService<Order> {

  @Autowired
  OrderRepository orderRepository;

  public Order createOrder(Order newOrder){
    newOrder.setInitialQuantity(newOrder.getQuantity());
    newOrder.setStatus("unfulfilled");
    newOrder.setDate(this.getFormattedTime());
    if( newOrder.isBid() == true){
      newOrder.setOrderType("bid");
    }else{
       newOrder.setOrderType("ask");
    }

    return newOrder;
  }

  private String getFormattedTime() {
    LocalDateTime currentDateTime = LocalDateTime.now();

    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");

    String formattedDateTime = currentDateTime.format(formatter);
    return formattedDateTime;
  }

  @Override
  public Order addItem(Order order) {
    return orderRepository.save(order);
  }

  @Override
  public Order findItemById(int id) {
    try {
      return orderRepository.findById(id).get(0);
    } catch (Exception e) {
      System.out.println("OrderRepoService" + e);
      return null;
    }
    
  }

  @Override
  public Order findItemsById() {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'findItemsById'");
  }

}
