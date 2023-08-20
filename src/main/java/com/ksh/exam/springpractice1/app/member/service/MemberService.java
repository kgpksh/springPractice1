package com.ksh.exam.springpractice1.app.member.service;

import com.ksh.exam.springpractice1.app.member.dto.Member;
import com.ksh.exam.springpractice1.app.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;

    public Member getMemberByUserName(String username) {
        return memberRepository.getMemberByUserName(username);
    }
}
