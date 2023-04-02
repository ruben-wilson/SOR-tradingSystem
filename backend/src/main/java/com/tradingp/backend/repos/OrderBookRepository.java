package com.tradingp.backend.repos;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tradingp.backend.entities.OrderBook;


public interface OrderBookRepository extends JpaRepository<OrderBook, Integer> {

  List<OrderBook> findById(int id);
}
