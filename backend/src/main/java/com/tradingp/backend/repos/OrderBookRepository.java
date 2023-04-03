package com.tradingp.backend.repos;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.tradingp.backend.entities.OrderBook;


public interface OrderBookRepository extends JpaRepository<OrderBook, Integer> {

  List<OrderBook> findById(int id);

  @Query("SELECT ob FROM OrderBook ob WHERE ob.symbol = :symbol")
  List<OrderBook> findBySymbol(String symbol);

  
  // @Modifying
  // @Query("UPDATE orderBook o SET o.name = :name, p.description = :description, p.price = :price WHERE p.id = :id")
  // int updateProduct(@Param("id") Long id, @Param("name") String name, @Param("description") String description, @Param("price") BigDecimal price);

}
