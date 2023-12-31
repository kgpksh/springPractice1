package com.ksh.exam.springpractice1.app.article.controller;

import com.ksh.exam.springpractice1.app.article.dto.Article;
import com.ksh.exam.springpractice1.app.article.service.ArticleService.ArticleService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/adm/article")
@RequiredArgsConstructor
@Slf4j
public class AdmArticleController {
    private final ArticleService articleService;
    @GetMapping("/list")
    public String showList(Model model) {
        List<Article> articles = articleService.getForPrintArticles();

        log.debug("articles : " + articles);
        model.addAttribute("articles", articles);
        return "adm/article/list";
    }

    @GetMapping("/{id}")
    public String showDetail(@PathVariable Long id) {
        return "adm/article/detail";
    }

    @GetMapping("/write")
    public String showWrite() {
        return "adm/article/write";
    }
}
