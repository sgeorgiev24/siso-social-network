package com.github.sgeorgiev24.sisosocialnetwork.repository;

import com.github.sgeorgiev24.sisosocialnetwork.data.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, String> {
  Role findByAuthority(String authority);
}
