package com.tradingp.backend.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tradingp.backend.entities.Order;
import com.tradingp.backend.repos.OrderRepository;

@Service
public class OrderRepoService implements repoService<Order> {

  @Autowired
  OrderRepository orderRepository;

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
