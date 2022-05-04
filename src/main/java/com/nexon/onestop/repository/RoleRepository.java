package com.nexon.onestop.repository;

import com.nexon.onestop.domain.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository  extends JpaRepository<Role,Long> {

    Role findByroleName(String name);

}
