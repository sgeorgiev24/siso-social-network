package com.github.sgeorgiev24.sisosocialnetwork.repository;

import com.github.sgeorgiev24.sisosocialnetwork.data.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, String> {
  Optional<User> findByUsername(String username);
}
