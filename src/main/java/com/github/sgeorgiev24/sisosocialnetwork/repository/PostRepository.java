package com.github.sgeorgiev24.sisosocialnetwork.repository;

import com.github.sgeorgiev24.sisosocialnetwork.data.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepository extends JpaRepository<Post, String> {
}
