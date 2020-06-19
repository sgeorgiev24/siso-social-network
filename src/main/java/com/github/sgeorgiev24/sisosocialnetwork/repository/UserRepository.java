package com.github.sgeorgiev24.sisosocialnetwork.repository;

import com.github.sgeorgiev24.sisosocialnetwork.data.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, String> {
}
