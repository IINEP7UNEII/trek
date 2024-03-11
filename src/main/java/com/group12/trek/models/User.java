package com.group12.trek.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "users")
public class User {
  // user ID
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int uid;

  private String username;
  private String password;
  private boolean admin;

  //constant string value:A token for creating admin account 
  private static final String ADMIN_TOKEN = "Group12!!!";

  // constructor
  public User(String userName, String password, String token) {
    this.username = userName;
    this.password = password;
    if(token != null && token.equals(ADMIN_TOKEN)){
      this.admin = true;
    }
    else{
      this.admin = false;
    }
  }

  // getters
  public int getUid() {
    return uid;
  }

  public String getUserName() {
    return username;
  }

  public String getPassword() {
    return password;
  }

  public boolean isAdmin() {
    return admin;
  }

  // setters
  public void setUid(int uid) {
    this.uid = uid;
  }

  public void setUserName(String userName) {
    this.username = userName;
  }

  public void setPassword(String password) {
    this.password = password;
  }

}
