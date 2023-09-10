package Spike.springboot.first.controller;

import Spike.springboot.first.dto.ArticleForm;
import Spike.springboot.first.entity.Article;
import Spike.springboot.first.repository.ArticleRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Slf4j
@Controller
public class ArticleController {
    @Autowired
    private ArticleRepository articleRepository;
    @GetMapping("/articles/new")
    public String newArticleForm() {
        return "articles/new";
    }

    @PostMapping("/article/create")
    public String createArticle(@ModelAttribute ArticleForm articleForm) {
        log.info(articleForm.toString());

        // 1. DTO를 엔티티로 변환
        Article article = articleForm.toEntity();
        log.info(article.toString());
        // 2. 레포지토리로 엔티티를 DB에 저장
        Article savedArticle = articleRepository.save(article);
        log.info(savedArticle.toString());
        return "";
    }
}
