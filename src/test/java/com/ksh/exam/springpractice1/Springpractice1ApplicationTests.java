package com.ksh.exam.springpractice1;

import com.ksh.exam.springpractice1.app.article.dto.Article;
import com.ksh.exam.springpractice1.app.article.service.ArticleService.ArticleService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

@SpringBootTest
@Transactional
class Springpractice1ApplicationTests {
    @Autowired
    private ArticleService articleService;

    @Test
    @DisplayName("기본 읽기 테스트")
    void initRead() {
        List<Article> articles = articleService.getArticles();
        System.out.println(articles);
    }

    @Test
    @DisplayName("기본 쓰기 테스트")
    void initWrite() {
        long id = articleService.writeArticle("제목2", "내용2");

        assertThat(id).isGreaterThan(0L);
    }

    @Test
    @DisplayName("게시물 단건 조회")
    void readOne() {
        Article queryResult = articleService.getArticle(1L);
        assertThat(queryResult.getSubject()).isEqualTo("제목1");
    }

    @Test
    @DisplayName("게시글 제목으로 조회")
    void searchBySubject() {
        List<Article> articles = articleService.searchByKeyword("subject", "1");
        assertThat(articles.size()).isEqualTo(1);

        articles = articleService.searchByKeyword("content", "내용");
        assertThat(articles.size()).isEqualTo(1);
    }
}
