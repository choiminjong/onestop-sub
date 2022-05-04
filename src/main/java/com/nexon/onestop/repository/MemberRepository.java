package com.nexon.onestop.repository;

import com.nexon.onestop.domain.entity.Delegate;
import com.nexon.onestop.domain.entity.Member;
import com.nexon.onestop.domain.entity.Team;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member,Long> {
   // Member findByUsername(String username);
}
