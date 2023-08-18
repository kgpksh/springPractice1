package com.ksh.exam.springpractice1.app.article.repository;

import com.ksh.exam.springpractice1.app.article.dto.Article;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface ArticleRepository {

    @Select("""
            <script>
                SELECT *
                FROM article
            </script>
            """)
    List<Article> getArticles();

    @Select("""
                <script>
                    INSERT INTO article
                    SET createDate = NOW(),
                    modifyDate = NOW(),
                    subject = #{subject},
                    content = #{content}
                </script>
            """)
    void write(String subject, String content);


    @Select("""
                SELECT LAST_INSERT_ID()
            """)
    long getLastInsertId();
}