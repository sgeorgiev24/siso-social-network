package com.github.sgeorgiev24.sisosocialnetwork.model.service;

public class RoleServiceModel {
  private String id;
  private String authority;

  public RoleServiceModel() {
  }

  public RoleServiceModel(String id, String authority) {
    this.id = id;
    this.authority = authority;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getAuthority() {
    return authority;
  }

  public void setAuthority(String authority) {
    this.authority = authority;
  }
}
