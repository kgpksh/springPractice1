package com.ksh.exam.springpractice1.app.member.controller;

import com.ksh.exam.springpractice1.app.base.Rq;
import com.ksh.exam.springpractice1.app.member.dto.Member;
import com.ksh.exam.springpractice1.app.member.service.MemberService;
import com.ksh.exam.springpractice1.util.Util;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/member")
@RequiredArgsConstructor
public class MemberController {
    private final MemberService memberService;
    private final Rq rq;
    @GetMapping("/login")
    public String showLogin() {
        return "member/login";
    }

    @PostMapping("/login")
    public String login(String username, String password, HttpSession session) {
        Member member = memberService.getMemberByUserName(username);
        if (member == null) {
            return "redirect:/?msg=" + Util.url.encode("존재하지 않는 회원입니다.");
        }

        if (member.matchPassword(password) == false) {
            return "redirect:/?msg=" + Util.url.encode("패스워드가 일치하지 않습니다.");
        }

        rq.setName(member.getName());
        session.setAttribute("loginedMemberId", member.getId());
        return "redirect:/?msg=" + Util.url.encode("로그인 성공");
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.removeAttribute("loginedMemberId");

        return "redirect:/?msg=" + Util.url.encode("로그아웃 성공");
    }
}
