package com.github.sgeorgiev24.sisosocialnetwork.model.service;

import org.springframework.web.multipart.MultipartFile;

import java.util.Set;

public class UserServiceModel {
  private String username;
  private String email;
  private String password;
  private String confirmPassword;
  private String profileImageUrl;
  private Set<RoleServiceModel> authority;

  public UserServiceModel() {
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
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

  public String getConfirmPassword() {
    return confirmPassword;
  }

  public void setConfirmPassword(String confirmPassword) {
    this.confirmPassword = confirmPassword;
  }

  public String getProfileImageUrl() {
    return profileImageUrl;
  }

  public void setProfileImageUrl(String profileImageUrl) {
    this.profileImageUrl = profileImageUrl;
  }

  public Set<RoleServiceModel> getAuthority() {
    return authority;
  }

  public void setAuthority(Set<RoleServiceModel> authority) {
    this.authority = authority;
  }
}
