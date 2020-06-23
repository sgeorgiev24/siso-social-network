package com.github.sgeorgiev24.sisosocialnetwork.web.controller;

import com.github.sgeorgiev24.sisosocialnetwork.model.rest.PostRequestModel;
import com.github.sgeorgiev24.sisosocialnetwork.model.service.PostServiceModel;
import com.github.sgeorgiev24.sisosocialnetwork.model.view.PostViewModel;
import com.github.sgeorgiev24.sisosocialnetwork.service.PostService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/posts")
public class PostApiController extends BaseController {
  private final PostService postService;
  private final ModelMapper modelMapper;

  @Autowired
  public PostApiController(PostService postService, ModelMapper modelMapper) {
    this.postService = postService;
    this.modelMapper = modelMapper;
  }

  @GetMapping("/all")
  @PreAuthorize("isAuthenticated()")
  public List<PostViewModel> getAllPosts() {
    return postService.getAllPosts()
            .stream()
            .map(post -> modelMapper.map(post, PostViewModel.class))
            .collect(Collectors.toList());
  }
}
