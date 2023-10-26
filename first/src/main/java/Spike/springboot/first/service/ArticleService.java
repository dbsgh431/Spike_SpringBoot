package Spike.springboot.first.service;


import Spike.springboot.first.dto.ArticleForm;
import Spike.springboot.first.entity.Article;
import Spike.springboot.first.repository.ArticleRepository;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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

    @Transactional
    public List<Article> createArticles(List<ArticleForm> forms) {
        // 1. dto 묶을을 엔티티로 변환
        List<Article> articleList = forms.stream().map(dto -> dto.toEntity()).collect(Collectors.toList());
        // 2. 엔티티 묶음을 db에 저장
        articleList.stream().forEach(article -> articleRepository.save(article));
        // 3. 강제 예외 발생
        articleRepository.findById(-1L).orElseThrow(() -> new IllegalArgumentException("실패!"));

        return articleList;
    }
}
