package Spike.springboot.first.service;


import Spike.springboot.first.dto.ArticleForm;
import Spike.springboot.first.entity.Article;
import Spike.springboot.first.repository.ArticleRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class ArticleService {

    @Autowired
    private ArticleRepository articleRepository;


    public List<Article> index() {
        return articleRepository.findAll();
    }

    public Article show(Long id) {
        return articleRepository.findById(id).orElse(null);
    }

    public Article create(ArticleForm form) {
        Article article = form.toEntity();
        if (article.getId() != null) {
            return null;
        }
        return articleRepository.save(article);
    }

    public Article update(Long id, ArticleForm form) {
        // DTO -> Entity 변환
        Article article = form.toEntity();
        log.info("요청 id={}, article={}", id, article.toString());
        // 타깃 조회
        Article target = articleRepository.findById(id).orElse(null);
        // 검증
        if (target == null || id != article.getId()) {
            // 잘못된 요청이므로 400 응답
            log.info("잘못된 요청입니다. id={}, article={}", id, article.toString());
            return null;
        }
        // 업데이트
        target.patch(article);
        Article save = articleRepository.save(target);
        return save;


    }

    public Article delete(Long id) {
        // 삭제 대상 찾기
        Article article = articleRepository.findById(id).orElse(null);
        // 예외 처리
        if (article == null) {
            return null;
        }
        // 삭제 처리
        articleRepository.delete(article);
        return article;
    }
}
