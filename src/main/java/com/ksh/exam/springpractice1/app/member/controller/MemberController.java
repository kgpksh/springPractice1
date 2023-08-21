package com.ksh.exam.springpractice1.app.member.controller;

import com.ksh.exam.springpractice1.app.base.Rq;
import com.ksh.exam.springpractice1.app.member.dto.Member;
import com.ksh.exam.springpractice1.app.member.service.MemberService;
import com.ksh.exam.springpractice1.util.Util;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
@RequestMapping("/member")
@RequiredArgsConstructor
public class MemberController {
    private final MemberService memberService;
    private final Rq rq;

    @GetMapping("/me")
    @ResponseBody
    public Member getMe() {
        return rq.getLoginedMember();
    }
    @GetMapping("/login")
    public String showLogin() {
        return "member/login";
    }

    @PostMapping("/login")
    public String login(String username, String password) {
        Member member = memberService.getMemberByUserName(username);
        if (member == null) {
            return rq.historyBackTemplate("일치하는 회원이 없습니다.");
        }

        if (member.matchPassword(password) == false) {
            return rq.historyBackTemplate("패스워드가 일치하지 않습니다.");
        }

        rq.setLoginDone(member);
        return "redirect:/?msg=" + Util.url.encode("로그인 성공");
    }

    @GetMapping("/logout")
    public String logout() {
        rq.setLogoutDone();

        return "redirect:/?msg=" + Util.url.encode("로그아웃 성공");
    }
}
