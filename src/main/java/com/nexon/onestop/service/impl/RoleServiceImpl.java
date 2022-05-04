package com.nexon.onestop.service.impl;

import com.nexon.onestop.domain.entity.Role;
import com.nexon.onestop.repository.RoleRepository;
import com.nexon.onestop.service.RoleService;

public class RoleServiceImpl implements RoleService {

    private RoleRepository roleRepository;

    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public void createRole(Role role) {
        roleRepository.save(role);
    }
}
