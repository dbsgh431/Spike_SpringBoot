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
import java.util.List;
import java.util.Optional;


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

    @GetMapping("/articles/{id}/edit")
    public String edit(@PathVariable Long id, Model model) {
        // 1. 수정할 데이터 가져오기
        Article foundArticle = articleRepository.findById(id).orElse(null);
        model.addAttribute("article", foundArticle);
        // 2. 수정 뷰 매핑하기
        return "articles/edit";
    }

    @PostMapping("/articles/update")
    public String update(ArticleForm form) {
        log.info(form.toString());
        // 1. DTO를 엔티티로 변환하기
        Article article = form.toEntity();
        // 2. 엔티티를 DB에 저장하기
        // 2-1. DB에서 기존 데이터 가져오기
        Article foundArticle = articleRepository.findById(article.getId()).orElse(null);
        // 2-2. 데이터 갱신하기
        if (foundArticle != null) {
            articleRepository.save(article);
        }
        // 3. 수정 결과 페이지로 리다이렉트 하기
        return "redirect:/articles/" +
                article.getId();
    }

    @GetMapping("/articles/{id}/delete")
    public String delete(@PathVariable Long id) {
        Article foundArticle = articleRepository.findById(id).orElse(null);
        log.info("삭제 요청 = {}", foundArticle.toString());
        if (foundArticle != null) {
            articleRepository.delete(foundArticle);
        }

        return "redirect:/articles";
    }
}
