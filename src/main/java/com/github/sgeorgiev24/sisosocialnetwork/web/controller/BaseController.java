package com.github.sgeorgiev24.sisosocialnetwork.web.controller;

import org.springframework.web.servlet.ModelAndView;

public class BaseController {
  public ModelAndView view(String viewName, ModelAndView modelAndView) {
    modelAndView.setViewName(viewName);

    return modelAndView;
  }

  public ModelAndView view(
          String viewName,
          ModelAndView modelAndView,
          String title) {
    modelAndView.addObject("title", " - " + title);
    modelAndView.setViewName(viewName);

    return modelAndView;
  }

  public ModelAndView view(String viewName) {
    return this.view(viewName, new ModelAndView());
  }

  public ModelAndView view(String viewName, String title) {
    return this.view(viewName, new ModelAndView(), title);
  }

  public ModelAndView redirect(String url) {
    return this.view("redirect:" + url);
  }

  public ModelAndView redirect(String url, String title) {
    return this.view("redirect:" + url, title);
  }
}
