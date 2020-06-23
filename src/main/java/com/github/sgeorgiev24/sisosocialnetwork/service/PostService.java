package com.github.sgeorgiev24.sisosocialnetwork.service;

import com.github.sgeorgiev24.sisosocialnetwork.model.service.PostServiceModel;

import java.util.List;

public interface PostService {
  List<PostServiceModel> getAllPosts();

  void createPost(String content, String creatorUsername);
}
