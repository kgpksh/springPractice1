package com.ksh.exam.springpractice1.app.base;

import com.ksh.exam.springpractice1.app.member.dto.Member;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.RequestScope;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Component
@RequestScope
@RequiredArgsConstructor
public class Rq {
    private final HttpServletRequest req;
    private final HttpServletResponse resp;
    private final HttpSession session;
    @Getter
    private int count;
    @Getter
    @Setter
    private String name;
    @Getter
    private String alertMsg;

    public void increaseCount() {
        count++;
    }

    public String getCurrentUrl() {
        return req.getRequestURL() + "?" + req.getQueryString();
    }

    public boolean isLogined() {
        return getLoginedMemberId() > 0;
    }

    public boolean isLogout() {
        return isLogined() == false;
    }

    public Member getLoginedMember() {
        long id = getLoginedMemberId();
        String name = getLoginedMemberName();
        String userName = getLoginedMemberUsername();
        String roles = getLoginedMemberRoles();
        String email = getLoginedMemberEmail();

        return Member.builder().
                id(id).
                roles(roles).
                email(email).
                name(name).
                username(userName).
                build();
    }

    public long getLoginedMemberId() {
        Long loginedMemberId = (Long) session.getAttribute("loginedMemberId");

        if (loginedMemberId == null) return 0;

        return loginedMemberId;
    }

    private String getLoginedMemberRoles() {
        return (String) session.getAttribute("loginedMemberRoles");
    }

    public String getLoginedMemberUsername() {
        return (String) session.getAttribute("loginedMemberUsername");
    }

    public String getLoginedMemberName() {
        return (String) session.getAttribute("loginedMemberName");
    }

    public String getLoginedMemberEmail() {
        return (String) session.getAttribute("loginedMemberEmail");
    }


    public void setLoginDone(Member member) {
        session.setAttribute("loginedMemberId", member.getId());
        session.setAttribute("loginedMemberRoles", member.getRoles());
        session.setAttribute("loginedMemberName", member.getName());
        session.setAttribute("loginedMemberUsername", member.getUsername());
        session.setAttribute("loginedMemberEmail", member.getEmail());
    }

    public void setLogoutDone() {
        session.removeAttribute("loginedMemberId");
        session.removeAttribute("loginedMemberRoles");
        session.removeAttribute("loginedMemberName");
        session.removeAttribute("loginedMemberUsername");
        session.removeAttribute("loginedMemberEmail");
    }

    public String historyBackTemplate(String msg) {
        alertMsg = msg;
        return "common/js";
    }

    public boolean isAdmin() {
        if (isLogout()) return false;
        return getLoginedMember().hasRole("ADMIN");
    }
}
