package com.nexon.onestop.service.impl;

import com.nexon.onestop.domain.entity.Role;
import com.nexon.onestop.domain.entity.Account;
import com.nexon.onestop.repository.RoleRepository;
import com.nexon.onestop.repository.UserRepository;
import com.nexon.onestop.service.UserService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.HashSet;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;
    private RoleRepository roleRepository;
    private PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }


    @Transactional
    @Override
    public void createUser(Account account) {

        Role role = roleRepository.findByroleName("ROLE_USER");
        Set<Role> roles = new HashSet<>();
        roles.add(role);
        account.setUserRoles(roles);

        account.setPassword((passwordEncoder.encode(account.getPassword())));
        userRepository.save(account);
    }

}
