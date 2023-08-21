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
                    SELECT *
                    FROM article
                    WHERE id = #{id}
                </script>
            """)
    Article getArticle(long id);

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

    @Select("""
                <script>
                    SELECT A.*
                    FROM article AS A
                    WHERE 1
                    <if test = "keyword != ''">
                        <choose>
                            <when test = "keywordType == 'subject'">
                                AND A.subject LIKE CONCAT('%', #{keyword}, '%')
                            </when>
                            
                            <when test = "keywordType == 'content'">
                                AND A.content LIKE CONCAT('%', #{keyword}, '%')
                            </when>
                            
                            <otherwise>
                                AND (
                                    A.subject LIKE CONCAT('%', #{keyword}, '%')
                                    OR
                                    A.content LIKE CONCAT('%', #{keyword}, '%')
                                )
                            </otherwise>
                        </choose>
                        
                    </if>
                </script>
            """)
    List<Article> searchByKeyword(String keywordType, String keyword);

    @Select("""
                <script>
                    SELECT 
                        A.*, 
                        M.username AS extra_member_username,
                        M.name AS extra_member_name
                    FROM article as A
                    LEFT JOIN  member as M
                        ON A.memberId = M.id
                </script>
            """)
    List<Article> getForPrintArticles();
}