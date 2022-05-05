package com.nexon.onestop.controller.api;

import com.nexon.onestop.domain.entity.Account;
import com.nexon.onestop.domain.entity.Member;
import com.nexon.onestop.domain.entity.Team;
import com.nexon.onestop.repository.MemberRepository;
import com.nexon.onestop.repository.TeamRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class DummyControllerTest {

    @Autowired
    private TeamRepository teamRepository;

    @Autowired
    private MemberRepository memberRepository;

    @PostMapping("/dummy/setup")
    public String setUp(){
        Team team = Team.builder()
                .name("TeamA")
                .build();

        teamRepository.save(team);

        Member member = Member.builder()
                .username("member1")
                .team(team)
                .build();

        memberRepository.save(member);
        return "팀추가";
    }

    @PostMapping("/dummy/team/{teamname}")
    public String teamAdd(@PathVariable String teamname){
        Team team = Team.builder()
                .name(teamname)
                .build();

        teamRepository.save(team);
        return "팀추가";
    }


    @PostMapping("/dummy/member/{teamname}/{membername}")
    public String teamMemberAdd(@PathVariable String teamname,
                                @PathVariable String membername) {

        Team teamAdd = teamRepository.findByname(teamname);
        Member member = Member.builder()
                        .username(membername)
                        .team(teamAdd)
                        .build();

        memberRepository.save(member);
        return "멤버 추가";

    }

    @GetMapping("/dummy/team")
    public List<Team> getTeam(){
        List<Team> teamsMembers = teamRepository.findAll();
        System.out.println("teamsMembers = " + teamsMembers);
        return teamsMembers;
    }

    @GetMapping("/dummy/member")
    public List<Member> getMember(){
        return  memberRepository.findAll();
    }


}