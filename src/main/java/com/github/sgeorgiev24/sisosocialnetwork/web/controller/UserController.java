package com.github.sgeorgiev24.sisosocialnetwork.web.controller;

import com.github.sgeorgiev24.sisosocialnetwork.external.CloudinaryService;
import com.github.sgeorgiev24.sisosocialnetwork.model.binding.UserRegisterBindingModel;
import com.github.sgeorgiev24.sisosocialnetwork.model.service.UserServiceModel;
import com.github.sgeorgiev24.sisosocialnetwork.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;

@Controller
@RequestMapping("/users")
public class UserController extends BaseController {
  private final UserService userService;
  private final ModelMapper modelMapper;
  private final CloudinaryService cloudinaryService;

  @Autowired
  public UserController(
          UserService userService,
          ModelMapper modelMapper,
          CloudinaryService cloudinaryService) {
    this.userService = userService;
    this.modelMapper = modelMapper;
    this.cloudinaryService = cloudinaryService;
  }

  @GetMapping("/register")
  @PreAuthorize("isAnonymous()")
  public ModelAndView getRegister() {
    return view("users/register", "Register");
  }

  @PostMapping("/register")
  @PreAuthorize("isAnonymous()")
  public ModelAndView postRegister(
          @ModelAttribute UserRegisterBindingModel model)
          throws IOException {
    if (!model.getPassword().equals(model.getConfirmPassword())) {
      return view("users/register", "Register");
    }

    UserServiceModel userServiceModel = modelMapper
            .map(model, UserServiceModel.class);
    userServiceModel.setProfileImageUrl(cloudinaryService
            .uploadImage(model.getProfileImageUrl()));

    userService.registerUser(userServiceModel);

    return redirect("login", "Login");
  }
}