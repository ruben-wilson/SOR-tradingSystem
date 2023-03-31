package com.tradingp.backend.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tradingp.backend.entities.Order;
import com.tradingp.backend.repositories.OrderRepository;

@Service
public class OrderService {

  @Autowired
  OrderRepository orderRepository;

  public List<Order> findById(int id){
    return orderRepository.findById(id);
  }
  
  // method to add order
  public Order addOrder(Order order) {
    return orderRepository.save(order);
  }


  
}
