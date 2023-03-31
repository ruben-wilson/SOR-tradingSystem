package com.tradingp.backend.services;

public interface repoService<T> {
  public T addItem();

  public T findItemById();

  public T findItemsById();
}
