package com.tradingp.backend.services;

public interface repoService<T> {
  public T addItem(T obj);

  public T findItemById(int id);

  public T findItemsById();
}
