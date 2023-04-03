package com.tradingp.backend.services.orderBook;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tradingp.backend.repos.OrderBookRepository;
import com.tradingp.backend.entities.OrderBook;
import com.tradingp.backend.services.repoService;

@Service
public class OrderBookRepoService implements repoService<OrderBook> {
  @Autowired
  OrderBookRepository orderBookRepository;

  
  @Override
  public OrderBook addItem(OrderBook order) {
    return orderBookRepository.save(order);
  }

  @Override
  public OrderBook findItemById(int id) {
    try {
      return orderBookRepository.findById(id).get(0);
    } catch (Exception e) {
      System.out.println("OrderBookRepoService :" + e);
      return null;
    }
   
  }

  // public OrderBook updateOrderBook(){

  // }

  @Override
  public OrderBook findItemsById() {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'findItemsById'");
  }

  

}
