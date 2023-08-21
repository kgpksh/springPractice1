package com.ksh.exam.springpractice1.app.article.controller;

import com.ksh.exam.springpractice1.app.article.dto.Article;
import com.ksh.exam.springpractice1.app.article.service.ArticleService.ArticleService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/article")
@RequiredArgsConstructor
@Slf4j
public class ArticleController {
    private final ArticleService articleService;
    @GetMapping("/list")
    public String showList(Model model) {
        List<Article> articles = articleService.getArticles();

        log.debug("articles : " + articles);
        model.addAttribute("articles", articles);
        return "article/list";
    }

    @GetMapping("/write")
    public String showWrite() {
        return "article/write";
    }
}
