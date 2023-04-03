package com.tradingp.backend.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tradingp.backend.entities.Order;

public interface OrderRepository extends JpaRepository<Order, Integer>{

  List<Order> findByOrderType(String orderType);

  List<Order> findByStatus(String status);

  List<Order> findById(int id);
  
}
