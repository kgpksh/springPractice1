package com.ksh.exam.springpractice1.app.member.dto;

import lombok.Data;

@Data
public class Member {
    private long id;
    private String username;
    private String password;
    private String name;
    private String email;

    public boolean matchPassword(String password) {
        return this.password.equals(password);
    }
}