package com.tradingp.backend.services.orderBook;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tradingp.backend.repos.OrderBookRepository;
import com.tradingp.backend.repos.OrderRepository;
import com.tradingp.backend.entities.Order;
import com.tradingp.backend.entities.OrderBook;
import com.tradingp.backend.services.repoService;

@Service
public class OrderBookRepoService implements repoService<OrderBook> {
  @Autowired
  OrderBookRepository orderBookRepository;

  @Autowired
  OrderRepository orderRepository;

  
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

  public OrderBook findOrderBookBySymbol(String symbol){
    try {
      return orderBookRepository.findBySymbol(symbol).get(0);
    } catch (Exception e) {
      System.out.println("\n OrderBookRepoService findOrderBookBySymbol :" + e);
      return null;
    }
   
  }

  public OrderBook saveLiveOrderBook(OrderBook orderBook){
    orderBook.getOrderList().stream()
                            .forEach(o -> o.setOrderBook(orderBook));
    return this.addItem(orderBook);
  }

  // public OrderBook updateLiveOrderBook(OrderBook orderBook){    

    
  //   orderRepository.deleteByOrders(orderBook);

  //   orderBook.getOrderList().stream()
  //                           .forEach(o -> o.setOrderBook(orderBook));
  //   return orderBook;
  // }

  @Override
  public OrderBook findItemsById() {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'findItemsById'");
  }

  

}
