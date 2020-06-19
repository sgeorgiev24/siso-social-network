package com.github.sgeorgiev24.sisosocialnetwork.service.implementation;

import com.github.sgeorgiev24.sisosocialnetwork.data.entity.Role;
import com.github.sgeorgiev24.sisosocialnetwork.model.service.RoleServiceModel;
import com.github.sgeorgiev24.sisosocialnetwork.repository.RoleRepository;
import com.github.sgeorgiev24.sisosocialnetwork.service.RoleService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@Service
public class RoleServiceImplementation implements RoleService {
  private final RoleRepository roleRepository;
  private final ModelMapper modelMapper;

  @Autowired
  public RoleServiceImplementation(
          RoleRepository roleRepository,
          ModelMapper modelMapper) {
    this.roleRepository = roleRepository;
    this.modelMapper = modelMapper;
  }

  @Override
  public void seedRolesInDb() {
    if (roleRepository.count() == 0) {
      roleRepository.save(new Role("ROLE_ADMIN"));
      roleRepository.save(new Role("ROLE_USER"));
    }
  }

  @Override
  public Set<RoleServiceModel> findAllRoles() {
    return roleRepository
            .findAll()
            .stream()
            .map(role -> this.modelMapper
                    .map(role, RoleServiceModel.class))
            .collect(Collectors.toSet());
  }

  @Override
  public RoleServiceModel findByAuthority(String authority) {
    Role role = roleRepository.findByAuthority(authority);

    return modelMapper.map(role, RoleServiceModel.class);
  }
}
