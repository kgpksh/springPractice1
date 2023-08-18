package com.ksh.exam.springpractice1.app.article.service.ArticleService;

import com.ksh.exam.springpractice1.app.article.dto.Article;
import com.ksh.exam.springpractice1.app.article.repository.ArticleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ArticleService {
    private final ArticleRepository articleRepository;
    public List<Article> getArticles() {
        return articleRepository.getArticles();
    }

    public Article getArticle(long id) {
        return articleRepository.getArticle(id);
    }

    public long writeArticle(String subject, String content) {
        articleRepository.write(subject, content);

        return articleRepository.getLastInsertId();
    }
}
