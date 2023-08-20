package com.ksh.exam.springpractice1.app.member.repository;

import com.ksh.exam.springpractice1.app.member.dto.Member;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface MemberRepository {

    @Select("""
                SELECT * FROM `member`
                WHERE username = #{username}
            """)
    Member getMemberByUserName(String username);
}
