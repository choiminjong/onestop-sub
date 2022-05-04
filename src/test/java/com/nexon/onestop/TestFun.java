package com.nexon.onestop;

import com.nexon.onestop.domain.entity.Member;
import com.nexon.onestop.domain.entity.Team;
import com.nexon.onestop.repository.MemberRepository;
import com.nexon.onestop.repository.TeamRepository;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;


@SpringBootTest
public class TestFun {

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private TeamRepository teamRepository;

    //추가된 부분
    @Autowired
    private EntityManager entityManager;

}
