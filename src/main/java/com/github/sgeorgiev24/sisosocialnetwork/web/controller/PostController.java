package com.github.sgeorgiev24.sisosocialnetwork.web.controller;

import com.github.sgeorgiev24.sisosocialnetwork.model.rest.PostRequestModel;
import com.github.sgeorgiev24.sisosocialnetwork.service.PostService;
import java.security.Principal;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/posts")
public class PostController extends BaseController {
  private final PostService postService;

  public PostController(PostService postService) {
    this.postService = postService;
  }

  @PostMapping("/add")
  @PreAuthorize("isAuthenticated()")
  public ModelAndView postAddPost(
          @ModelAttribute PostRequestModel model,
          Principal principal) {
    postService.createPost(model.getContent(), principal.getName());

    return redirect("/home", "Home");
  }
}
