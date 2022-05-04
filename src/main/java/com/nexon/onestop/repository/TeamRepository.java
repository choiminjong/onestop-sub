package com.nexon.onestop.repository;

import com.nexon.onestop.domain.entity.Account;
import com.nexon.onestop.domain.entity.Member;
import com.nexon.onestop.domain.entity.Team;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeamRepository extends JpaRepository<Team,Long> {
    Team findByname(String name);
}
