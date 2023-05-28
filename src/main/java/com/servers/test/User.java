package com.servers.test;

public class User {
  private String login;
  private String password;

  public User(String login, String password) {
    this.login = login;
    this.password = password;
  }

  public String getLogin() {
    return login;
  }

  public String getPassword() {
    return password;
  }

  public User setLogin(String login) {
    this.login = login;
    return this;
  }

  public User setPassword(String password) {
    this.password = password;
    return this;
  }
}
