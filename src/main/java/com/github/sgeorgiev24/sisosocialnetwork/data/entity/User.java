package com.github.sgeorgiev24.sisosocialnetwork.data.entity;

import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "users")
public class User extends BaseEntity implements UserDetails {
  private String username;
  private String email;
  private String password;
  private String profileImageUrl;
  private Set<Post> posts;
  private Set<Role> authorities;

  public User() {
  }

  @Override
  @Column(
          name = "username",
          nullable = false,
          updatable = false,
          unique = true,
          length = 20
  )
  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  @Column(
          name = "email",
          nullable = false,
          updatable = false,
          unique = true,
          length = 30
  )
  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  @Override
  @Column(name = "password", nullable = false)
  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  @Column(name = "profile_image_url")
  public String getProfileImageUrl() {
    return profileImageUrl;
  }

  public void setProfileImageUrl(String imageUrl) {
    this.profileImageUrl = imageUrl;
  }

  @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
  @JoinColumn(name = "creator")
  public Set<Post> getPosts() {
    return posts;
  }

  public void setPosts(Set<Post> posts) {
    this.posts = posts;
  }

  @Override
  @ManyToMany(
          targetEntity = Role.class,
          fetch = FetchType.EAGER,
          cascade = CascadeType.ALL)
  @JoinTable(
          name = "users_roles",
          joinColumns = @JoinColumn(
                  name = "user_id",
                  referencedColumnName = "id"
          ),
          inverseJoinColumns = @JoinColumn(
                  name = "role_id",
                  referencedColumnName = "id"
          )
  )
  public Set<Role> getAuthorities() {
    return authorities;
  }

  public void setAuthorities(Set<Role> authorities) {
    this.authorities = authorities;
  }

  @Override
  @Transient
  public boolean isAccountNonExpired() {
    return true;
  }

  @Override
  @Transient
  public boolean isAccountNonLocked() {
    return true;
  }

  @Override
  @Transient
  public boolean isCredentialsNonExpired() {
    return true;
  }

  @Override
  @Transient
  public boolean isEnabled() {
    return true;
  }
}
