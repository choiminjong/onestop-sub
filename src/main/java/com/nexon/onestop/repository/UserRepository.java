package com.nexon.onestop.repository;

import com.nexon.onestop.domain.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<Account, Long> {

    Account findByUsername(String username);

    int countByUsername(String username);
}
