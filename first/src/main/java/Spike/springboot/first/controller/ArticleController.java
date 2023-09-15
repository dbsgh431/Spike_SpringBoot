package Spike.springboot.first.controller;

import Spike.springboot.first.dto.ArticleForm;
import Spike.springboot.first.entity.Article;
import Spike.springboot.first.repository.ArticleRepository;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;

@Slf4j
@Controller
public class ArticleController {
    @Autowired
    private ArticleRepository articleRepository;
    @GetMapping("/articles/new")
    public String newArticleForm() {
        return "articles/new";
    }

    @PostMapping("/articles/create")
    public String createArticle(@ModelAttribute ArticleForm articleForm) {
        log.info(articleForm.toString());

        // 1. DTO를 엔티티로 변환
        Article article = articleForm.toEntity();
        log.info(article.toString());
        // 2. 레포지토리로 엔티티를 DB에 저장
        Article savedArticle = articleRepository.save(article);
        log.info(savedArticle.toString());
        return "redirect:/articles/" +
                savedArticle.getId();
    }

    @GetMapping("/articles/{id}")
    public String show(@PathVariable Long id, Model model) {
        log.info("id = " + id);
        Article foundArticle = articleRepository.findById(id).orElse(null);
        model.addAttribute("article", foundArticle);
        return "articles/show";
    }

    @GetMapping("/articles")
    public String index(Model model) {
        // 1. 모든 데이터 가져오기
        ArrayList<Article> articleList = articleRepository.findAll();
        // 2. 모델에 데이터 등록하기
        model.addAttribute("articleList", articleList);
        // 3. 뷰 페이지 설정하기
        return "articles/index";
    }
}
