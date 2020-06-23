package com.github.sgeorgiev24.sisosocialnetwork.service.implementation;

import com.github.sgeorgiev24.sisosocialnetwork.data.entity.Post;
import com.github.sgeorgiev24.sisosocialnetwork.data.entity.User;
import com.github.sgeorgiev24.sisosocialnetwork.model.service.PostServiceModel;
import com.github.sgeorgiev24.sisosocialnetwork.repository.PostRepository;
import com.github.sgeorgiev24.sisosocialnetwork.repository.UserRepository;
import com.github.sgeorgiev24.sisosocialnetwork.service.PostService;
import com.github.sgeorgiev24.sisosocialnetwork.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostServiceImplementation implements PostService {
  private final PostRepository postRepository;
  private final ModelMapper modelMapper;
  private final UserRepository userRepository;

  @Autowired
  public PostServiceImplementation(
          PostRepository postRepository,
          ModelMapper modelMapper,
          UserRepository userRepository) {
    this.postRepository = postRepository;
    this.modelMapper = modelMapper;
    this.userRepository = userRepository;
  }

  @Override
  public List<PostServiceModel> getAllPosts() {
    return postRepository
            .findAll(Sort.by(Sort.Direction.DESC, "dateCreated"))
            .stream()
            .map(post -> modelMapper.map(post, PostServiceModel.class))
            .collect(Collectors.toList());
  }

  @Override
  public void createPost(String content, String creatorUsername) {
    User creator = userRepository.findByUsername(creatorUsername).orElseThrow();
    Post post = new Post();
    post.setContent(content);
    post.setDateCreated(LocalDateTime.now());
    post.setCreator(modelMapper.map(creator, User.class));

    postRepository.save(post);
  }
}
