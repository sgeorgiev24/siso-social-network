package com.github.sgeorgiev24.sisosocialnetwork.service.implementation;

import com.github.sgeorgiev24.sisosocialnetwork.data.entity.User;
import com.github.sgeorgiev24.sisosocialnetwork.external.CloudinaryService;
import com.github.sgeorgiev24.sisosocialnetwork.model.service.UserServiceModel;
import com.github.sgeorgiev24.sisosocialnetwork.repository.RoleRepository;
import com.github.sgeorgiev24.sisosocialnetwork.repository.UserRepository;
import com.github.sgeorgiev24.sisosocialnetwork.service.RoleService;
import com.github.sgeorgiev24.sisosocialnetwork.service.UserService;
import java.util.LinkedHashSet;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImplementation implements UserService {
  private final RoleService roleService;
  private final RoleRepository roleRepository;
  private final ModelMapper modelMapper;
  private final BCryptPasswordEncoder bCryptPasswordEncoder;
  private final UserRepository userRepository;
  private final CloudinaryService cloudinaryService;

  @Autowired
  public UserServiceImplementation(
          RoleService roleService,
          RoleRepository roleRepository,
          ModelMapper modelMapper,
          BCryptPasswordEncoder bCryptPasswordEncoder,
          UserRepository userRepository,
          CloudinaryService cloudinaryService) {
    this.roleService = roleService;
    this.roleRepository = roleRepository;
    this.modelMapper = modelMapper;
    this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    this.userRepository = userRepository;
    this.cloudinaryService = cloudinaryService;
  }

  @Override
  public void registerUser(UserServiceModel userServiceModel) {
    roleService.seedRolesInDb();

    if (roleRepository.count() == 0) {
      userServiceModel.setAuthority(roleService.findAllRoles());
    } else {
      userServiceModel.setAuthority(new LinkedHashSet<>());
      userServiceModel.getAuthority().add(roleService
              .findByAuthority("ROLE_USER"));
    }

    User user = modelMapper.map(userServiceModel, User.class);
    user.setPassword(bCryptPasswordEncoder
            .encode(userServiceModel.getPassword()));

    userRepository.save(user);
  }

  @Override
  public UserServiceModel findByUsername(String username) {
    User user = userRepository
            .findByUsername(username)
            .orElseThrow(() ->
                    new UsernameNotFoundException("Username not found."));

    return modelMapper.map(user, UserServiceModel.class);
  }

  @Override
  public void editProfile(
          UserServiceModel userServiceModel,
          String oldPassword) {
    User user = userRepository
            .findByUsername(userServiceModel.getUsername())
            .orElseThrow(() ->
                    new UsernameNotFoundException("Username not found."));

    if (!bCryptPasswordEncoder.matches(oldPassword, user.getPassword())) {
      throw new IllegalArgumentException("Wrong password");
    }

    if (!userServiceModel.getPassword().equals("")) {
      user.setPassword(bCryptPasswordEncoder
              .encode(userServiceModel.getPassword()));
    }

    if (userServiceModel.getProfileImageUrl() != null) {
      user.setProfileImageUrl(userServiceModel.getProfileImageUrl());
    }

    userRepository.save(user);
  }

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    return userRepository.findByUsername(username)
            .orElseThrow(() ->
                    new UsernameNotFoundException("Username not found."));
  }
}
