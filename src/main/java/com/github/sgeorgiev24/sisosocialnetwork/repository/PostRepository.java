package com.github.sgeorgiev24.sisosocialnetwork.repository;

import com.github.sgeorgiev24.sisosocialnetwork.data.entity.Post;
import com.github.sgeorgiev24.sisosocialnetwork.data.entity.User;
import java.util.Set;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepository extends JpaRepository<Post, String> {
  Set<Post> findAllByCreator(User creator);
}
