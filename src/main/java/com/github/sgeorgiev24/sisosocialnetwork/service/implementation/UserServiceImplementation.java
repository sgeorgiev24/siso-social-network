package com.github.sgeorgiev24.sisosocialnetwork.service.implementation;

import com.github.sgeorgiev24.sisosocialnetwork.data.entity.User;
import com.github.sgeorgiev24.sisosocialnetwork.model.service.PostServiceModel;
import com.github.sgeorgiev24.sisosocialnetwork.model.service.UserServiceModel;
import com.github.sgeorgiev24.sisosocialnetwork.model.service.UserViewProfileServiceModel;
import com.github.sgeorgiev24.sisosocialnetwork.model.view.PostViewModel;
import com.github.sgeorgiev24.sisosocialnetwork.repository.PostRepository;
import com.github.sgeorgiev24.sisosocialnetwork.repository.RoleRepository;
import com.github.sgeorgiev24.sisosocialnetwork.repository.UserRepository;
import com.github.sgeorgiev24.sisosocialnetwork.service.RoleService;
import com.github.sgeorgiev24.sisosocialnetwork.service.UserService;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.stream.Collectors;
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
  private final PostRepository postRepository;

  @Autowired
  public UserServiceImplementation(
          RoleService roleService,
          RoleRepository roleRepository,
          ModelMapper modelMapper,
          BCryptPasswordEncoder bCryptPasswordEncoder,
          UserRepository userRepository,
          PostRepository postRepository) {
    this.roleService = roleService;
    this.roleRepository = roleRepository;
    this.modelMapper = modelMapper;
    this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    this.userRepository = userRepository;
    this.postRepository = postRepository;
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

    if (userServiceModel.getProfileImageUrl().startsWith("http")) {
      user.setProfileImageUrl(userServiceModel.getProfileImageUrl());
    }

    userRepository.save(user);
  }

  @Override
  public UserViewProfileServiceModel loadUserProfile(String username) {
    User user = userRepository.findByUsername(username)
            .orElseThrow(() ->
                    new UsernameNotFoundException("Username not found."));

    Set<PostServiceModel> postServiceModelSet = postRepository
            .findAllByCreator(user)
            .stream()
            .map(post -> modelMapper.map(post, PostServiceModel.class))
            .collect(Collectors.toSet());

    var result = modelMapper.map(user, UserViewProfileServiceModel.class);
    result.setPosts(postServiceModelSet);

    return result;
  }

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    return userRepository.findByUsername(username)
            .orElseThrow(() ->
                    new UsernameNotFoundException("Username not found."));
  }
}
