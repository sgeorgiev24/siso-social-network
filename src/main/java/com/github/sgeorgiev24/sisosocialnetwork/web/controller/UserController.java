package com.github.sgeorgiev24.sisosocialnetwork.web.controller;

import com.github.sgeorgiev24.sisosocialnetwork.external.CloudinaryService;
import com.github.sgeorgiev24.sisosocialnetwork.model.binding.UserEditBindingModel;
import com.github.sgeorgiev24.sisosocialnetwork.model.binding.UserRegisterBindingModel;
import com.github.sgeorgiev24.sisosocialnetwork.model.service.UserServiceModel;
import com.github.sgeorgiev24.sisosocialnetwork.model.service.UserViewProfileServiceModel;
import com.github.sgeorgiev24.sisosocialnetwork.service.UserService;
import java.io.IOException;
import java.security.Principal;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

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

    if (!"".equals(model.getProfileImageUrl().getOriginalFilename())) {
      userServiceModel.setProfileImageUrl(cloudinaryService
              .uploadImage(model.getProfileImageUrl()));
    }
    userService.registerUser(userServiceModel);

    return redirect("login", "Login");
  }

  @GetMapping("/login")
  @PreAuthorize("isAnonymous()")
  public ModelAndView getLogin() {
    return view("users/login", "Login");
  }

  @GetMapping("/profile")
  @PreAuthorize("isAuthenticated()")
  public ModelAndView getProfile(
          Principal principal,
          ModelAndView modelAndView) {
    UserServiceModel userServiceModel = userService
            .findByUsername(principal.getName());
    modelAndView.addObject("model", userServiceModel);

    return view("users/edit-profile", modelAndView);
  }

  @PostMapping("/profile")
  @PreAuthorize("isAuthenticated()")
  public ModelAndView postProfile(
          @ModelAttribute UserEditBindingModel model)
          throws IOException {
    UserServiceModel userServiceModel = modelMapper
            .map(model, UserServiceModel.class);

    if (!"".equals(model.getProfileImageUrl().getOriginalFilename())) {
      userServiceModel.setProfileImageUrl(cloudinaryService
              .uploadImage(model.getProfileImageUrl()));
    }

    userService.editProfile(userServiceModel, model.getOldPassword());

    return redirect("view/" + model.getUsername());
  }

  @GetMapping("/view/{username}")
  @PreAuthorize("isAuthenticated()")
  public ModelAndView getViewProfile(
          @PathVariable String username,
          ModelAndView modelAndView) {
    UserViewProfileServiceModel model = userService.loadUserProfile(username);
    modelAndView.addObject("model", model);

    return view("users/view-profile", modelAndView, username + " profile");
  }
}
