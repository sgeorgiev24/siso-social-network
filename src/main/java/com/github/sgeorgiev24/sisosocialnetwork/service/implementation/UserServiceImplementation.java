package com.github.sgeorgiev24.sisosocialnetwork.service.implementation;

import com.github.sgeorgiev24.sisosocialnetwork.data.entity.User;
import com.github.sgeorgiev24.sisosocialnetwork.model.service.UserServiceModel;
import com.github.sgeorgiev24.sisosocialnetwork.repository.RoleRepository;
import com.github.sgeorgiev24.sisosocialnetwork.repository.UserRepository;
import com.github.sgeorgiev24.sisosocialnetwork.service.RoleService;
import com.github.sgeorgiev24.sisosocialnetwork.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.LinkedHashSet;

@Service
public class UserServiceImplementation implements UserService {
  private final RoleService roleService;
  private final RoleRepository roleRepository;
  private final ModelMapper modelMapper;
  private final BCryptPasswordEncoder bCryptPasswordEncoder;
  private final UserRepository userRepository;

  @Autowired
  public UserServiceImplementation(
          RoleService roleService,
          RoleRepository roleRepository,
          ModelMapper modelMapper,
          BCryptPasswordEncoder bCryptPasswordEncoder,
          UserRepository userRepository) {
    this.roleService = roleService;
    this.roleRepository = roleRepository;
    this.modelMapper = modelMapper;
    this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    this.userRepository = userRepository;
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
}
