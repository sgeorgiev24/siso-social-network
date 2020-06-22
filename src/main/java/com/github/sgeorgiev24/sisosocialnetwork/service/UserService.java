package com.github.sgeorgiev24.sisosocialnetwork.service;

import com.github.sgeorgiev24.sisosocialnetwork.model.service.UserServiceModel;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {
  void registerUser(UserServiceModel userServiceModel);
}
