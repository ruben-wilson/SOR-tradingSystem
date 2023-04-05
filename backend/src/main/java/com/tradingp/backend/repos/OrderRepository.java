package com.tradingp.backend.repos;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.tradingp.backend.entities.Order;
import com.tradingp.backend.entities.OrderBook;

import jakarta.transaction.Transactional;

public interface OrderRepository extends JpaRepository<Order, Integer> {

  List<Order> findByOrderType(String orderType);

  List<Order> findByStatus(String status);

  List<Order> findById(int id);

  @Modifying
  @Transactional
  @Query("DELETE FROM Order o WHERE o.orderBook = :orderBook")
  void deleteByOrders(OrderBook orderBook);

}
