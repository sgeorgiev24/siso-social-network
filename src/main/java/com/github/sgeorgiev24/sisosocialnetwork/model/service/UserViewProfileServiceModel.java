package com.github.sgeorgiev24.sisosocialnetwork.model.service;

import java.util.Set;

public class UserViewProfileServiceModel {
  private String username;
  private String email;
  private String profileImageUrl;
  private Set<PostServiceModel> posts;

  public UserViewProfileServiceModel() {
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

  public String getProfileImageUrl() {
    return profileImageUrl;
  }

  public void setProfileImageUrl(String profileImageUrl) {
    this.profileImageUrl = profileImageUrl;
  }

  public Set<PostServiceModel> getPosts() {
    return posts;
  }

  public void setPosts(Set<PostServiceModel> posts) {
    this.posts = posts;
  }
}
