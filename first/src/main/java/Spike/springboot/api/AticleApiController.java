package Spike.springboot.api;

import Spike.springboot.first.dto.ArticleForm;
import Spike.springboot.first.entity.Article;
import Spike.springboot.first.repository.ArticleRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@RequiredArgsConstructor
public class AticleApiController {

    private final ArticleRepository articleRepository;

    //GET
    @GetMapping("/api/articles")
    public List<Article> index() {
        return articleRepository.findAll();
    }

    @GetMapping("/api/articles/{id}")
    public Article showById(@PathVariable Long id) {
        return articleRepository.findById(id).orElse(null);
    }

    //POST
    @PostMapping("/api/articles")
    public Article create(@RequestBody ArticleForm form) {
        Article article = form.toEntity();
        return articleRepository.save(article);
    }

    //DELETE
    //PATCH
    @PatchMapping("/api/articles/{id}")
    public ResponseEntity<Object> updateById(@PathVariable Long id, @RequestBody ArticleForm form) {
        // DTO -> Entity 변환
        Article article = form.toEntity();
        log.info("요청 id={}, article={}", id, article.toString());
        // 타깃 조회
        Article target = articleRepository.findById(id).orElse(null);
        // 검증
        if (target == null || id != article.getId()) {
            // 잘못된 요청이므로 400 응답
            log.info("잘못된 요청입니다. id={}, article={}", id, article.toString());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
        // 업데이트
        target.patch(article);
        Article save = articleRepository.save(target);
        return ResponseEntity.status(HttpStatus.OK).body(save);
    }
}
