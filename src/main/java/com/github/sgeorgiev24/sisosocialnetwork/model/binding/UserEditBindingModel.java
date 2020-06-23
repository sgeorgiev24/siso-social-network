package com.github.sgeorgiev24.sisosocialnetwork.model.binding;

import org.springframework.web.multipart.MultipartFile;

public class UserEditBindingModel {
  private String username;
  private String email;
  private String oldPassword;
  private String password;
  private String confirmPassword;
  private MultipartFile profileImageUrl;

  public UserEditBindingModel() {
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

  public String getOldPassword() {
    return oldPassword;
  }

  public void setOldPassword(String oldPassword) {
    this.oldPassword = oldPassword;
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

  public MultipartFile getProfileImageUrl() {
    return profileImageUrl;
  }

  public void setProfileImageUrl(MultipartFile profileImageUrl) {
    this.profileImageUrl = profileImageUrl;
  }
}
