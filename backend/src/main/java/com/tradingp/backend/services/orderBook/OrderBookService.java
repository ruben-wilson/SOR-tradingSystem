package com.tradingp.backend.services.orderBook;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.tradingp.backend.entities.Order;
import com.tradingp.backend.entities.OrderBook;

@Service
public class OrderBookService {

  private OrderBook orderBook;

  private List<Order> bids;
  private List<Order> asks;
  
  public void setOrderBook(OrderBook ord){
    this.orderBook = ord;
  }

	public Map<String, Object> matchOrder(Order order, OrderBook orderBook){

    Map<String, Object> output = new HashMap<>();
    // checks if there is no match possible or a trade error
    if(order == null || order.getPrice() == 0 || order.getQuantity() == 0){
       return null;
    }

    this.orderBook = orderBook;
    
    boolean isOrderBookEmpty = this.setBidsAndAsks(this.orderBook);
    if(isOrderBookEmpty){
      this.orderBook.addOrder(order);
      output.put("orderBook", this.orderBook);
      output.put("trades", null);
      return output;
    }

    List<Order> ordersAtLimit = this.findMatch(order);
    if(ordersAtLimit == null){
      this.orderBook.addOrder(order);
      output.put("orderBook", this.orderBook);
      output.put("trades", null);
      return output;
    }

    List<Order> executedTrades = this.executeTrades(ordersAtLimit, order);
    this.updateOrderBook(executedTrades);

    output.put("orderBook", this.orderBook);
    output.put("trades", executedTrades);

    return output;
  }


  public boolean setBidsAndAsks(OrderBook orderBook){

    if(orderBook.getBids() == null && orderBook.getAsks() == null){
      return true;
    }else{
      this.bids = orderBook.getBids();
      this.asks = orderBook.getAsks();
      return false;
    }

  }

  public List<Order> findMatch(Order order){
    return order.isBid() == true ? this.findBidMatch(order) : this.findAskMatch(order);
  }

  public List<Order> findBidMatch(Order incomingOrder){
    List<Order> positionsAtLimit = this.findLimit(incomingOrder, this.asks);

    if(positionsAtLimit == null){
      return null;
    }

    return positionsAtLimit;
  }

  public List<Order> findAskMatch(Order incomingOrder) {
   List<Order> positionsAtLimit = this.findLimit(incomingOrder, this.bids);

   if (positionsAtLimit == null) {
     return null;
   }

   return positionsAtLimit;
  }


  public List<Order> findLimit(Order incomingOrder, List<Order> openPositions ){
    if(openPositions == null){
      return null;
    }

    double limit = incomingOrder.getPrice();
    
    Collections.sort(openPositions, (p1, p2) -> Double.compare(p1.getPrice(), p2.getPrice()));
    List<Order> positionsAtLimit = new ArrayList<>(); 
    
    if(incomingOrder.isBid()){
        for(Order openPosition : openPositions ){

        if(limit >= openPosition.getPrice()){
          positionsAtLimit.add(openPosition);
        }
      }
    }else if(!incomingOrder.isBid()){
      for (Order openPosition : openPositions ){

          if(limit <= openPosition.getPrice()  ){
            positionsAtLimit.add(openPosition);
          }

        }
    }
    
    return positionsAtLimit.isEmpty() == true ? null : positionsAtLimit;
  }




  public List<Order> executeTrades(List<Order> ordersAtLimit, Order incomingOrder){

    double quantityToFill = incomingOrder.getQuantity();

    List<Order> executedTrades = new ArrayList<>();
    for(Order openPosition : ordersAtLimit){
      
        if(quantityToFill == openPosition.getQuantity()){
          incomingOrder.setQuantity(0);
          openPosition.setQuantity(0);
          quantityToFill = 0;

          executedTrades.add(incomingOrder);
          executedTrades.add(openPosition);
          break;             
        }else if(quantityToFill > openPosition.getQuantity()){

          quantityToFill -= openPosition.getQuantity();
          openPosition.setQuantity(0);

          executedTrades.add(openPosition);
        }else if(quantityToFill < openPosition.getQuantity()){

          openPosition.setQuantity(openPosition.getQuantity() - quantityToFill);
          quantityToFill = 0;

          incomingOrder.setQuantity(0);
          incomingOrder.setStatus("partiallyFilled");

          executedTrades.add(openPosition);
          executedTrades.add(incomingOrder);
          break;             
        }
      }

      if(quantityToFill > 0){
        incomingOrder.setQuantity(quantityToFill);
        this.orderBook.addOrder(incomingOrder);
      }

      return executedTrades;

    }

    private void updateOrderBook(List<Order> executedTrades) {

      for(Order executedTrade : executedTrades){

        if(executedTrade.getQuantity() == 0){
          this.orderBook.deleteOrder(executedTrade);
        }else if(executedTrade.getQuantity() > 0){
          this.orderBook.updateOrderQuantity(executedTrade.getOrderId(), executedTrade.getQuantity());
        }
        
      }
    }
  
  
}
