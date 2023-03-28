package com.tradingp.backend.entities;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class LogBook {
  
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int logBookId;

  @OneToMany(mappedBy = "LogBook", cascade = CascadeType.ALL)
  private List<Order> orderList;
  
}
