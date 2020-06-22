package com.github.sgeorgiev24.sisosocialnetwork.web.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HomeController extends BaseController {
  @GetMapping("/")
  @PreAuthorize("isAnonymous()")
  public ModelAndView getIndex() {
    return view("index");
  }

  @GetMapping("/home")
  @PreAuthorize("isAuthenticated()")
  public ModelAndView getHome() {
    return view("home");
  }
}
