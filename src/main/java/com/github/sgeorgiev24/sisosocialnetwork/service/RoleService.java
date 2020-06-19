package com.github.sgeorgiev24.sisosocialnetwork.service;

import com.github.sgeorgiev24.sisosocialnetwork.model.service.RoleServiceModel;

import java.util.Set;

public interface RoleService {
  void seedRolesInDb();

  Set<RoleServiceModel> findAllRoles();

  RoleServiceModel findByAuthority(String authority);
}
