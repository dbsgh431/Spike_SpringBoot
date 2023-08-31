package Spike.springboot.first.controller;

import Spike.springboot.first.dto.ArticleForm;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ArticleController {

    @GetMapping("/article")
    public String newArticleForm() {
        System.out.println("ArticleController.newArticleForm");
        return "articles/new";
    }

    @PostMapping("/article/create")
    public String createArticle(ArticleForm articleForm) {
        System.out.println(articleForm.toString());
        return "";
    }
}
