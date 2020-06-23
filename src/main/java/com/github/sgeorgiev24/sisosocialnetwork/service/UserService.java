package com.github.sgeorgiev24.sisosocialnetwork.service;

import com.github.sgeorgiev24.sisosocialnetwork.data.entity.User;
import com.github.sgeorgiev24.sisosocialnetwork.model.service.UserServiceModel;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {
  void registerUser(UserServiceModel userServiceModel);

  UserServiceModel findByUsername(String username);

  void editProfile(UserServiceModel userServiceModel, String oldPassword);
}
