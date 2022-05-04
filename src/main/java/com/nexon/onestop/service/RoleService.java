package com.nexon.onestop.service;

import com.nexon.onestop.domain.entity.Role;

import java.util.List;

public interface RoleService {
    Role getRole(long id);

    List<Role> getRoles();

    void createRole(Role role);

    void deleteRole(long id);
}
