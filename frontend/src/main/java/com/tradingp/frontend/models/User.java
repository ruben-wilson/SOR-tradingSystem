package com.tradingp.frontend.models;

import java.util.List;

public class User {
  
  private int id;
  private String firstname;
  private String lastname;
  private String email;
  private String password;

  private List <Order> orders;

  // Getters and Setters

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getFirstname() {
    return firstname;
  }

  public void setFirstname(String firstname) {
    this.firstname = firstname;
  }

  public String getLastname() {
    return lastname;
  }

  public void setLastname(String lastname) {
    this.lastname = lastname;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  // Constructors

  public User() {
  }

  public User(int id, String firstname, String lastname, String email, String password) {
    this.id = id;
    this.firstname = firstname;
    this.lastname = lastname;
    this.email = email;
    this.password = password;
  }

  public User(int id, String firstname, String lastname, String email, String password, List<Order> orders) {
    this.id = id;
    this.firstname = firstname;
    this.lastname = lastname;
    this.email = email;
    this.password = password;
    this.orders = orders;
  }

  // toString

  @Override
  public String toString() {
    return "User [email=" + email + ", firstname=" + firstname + ", id=" + id + ", lastname=" + lastname + ", password=" + password + ", orders="
        + orders + "]";
  }

}
