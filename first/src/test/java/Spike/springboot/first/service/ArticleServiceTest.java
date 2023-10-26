package Spike.springboot.first.service;

import Spike.springboot.first.dto.ArticleForm;
import Spike.springboot.first.entity.Article;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ArticleServiceTest {

    @Autowired
    ArticleService articleService = new ArticleService();


    @Test
    void index() {
        //given
        Article a = new Article(1L, "userA", "1111");
        Article b = new Article(2L, "userB", "2222");
        Article c = new Article(3L, "userC", "3333");

        List<Article> articles = new ArrayList<Article>(Arrays.asList(a, b, c));
        //when
        List<Article> target = articleService.index();
        //then
        assertThat(articles.size()).isEqualTo(target.size());
    }

    @Test
    void show_성공() {
        //given
        Article article = new Article(1L, "userA", "1111");
        //when
        Long id = 1L;
        Article target = articleService.show(id);
        //then
        assertThat(article.getTitle()).isEqualTo(target.getTitle());
    }

    @Test
    void show_실패() {
        //given
        Article article = new Article(1L, "userA", "1111");
        //when
        Long id = 2L;
        Article target = articleService.show(id);
        //then
        assertThat(article.getTitle()).isNotEqualTo(target.getTitle());
    }


    @Test
    @Transactional
    void create_성공() {
        //given
        String title = "userA";
        String content = "1111";
        ArticleForm form = new ArticleForm(null, title, content);
        Article expected = new Article(4L, title, content);
        //when
        Article target = articleService.create(form);
        //then
        assertThat(expected.getTitle()).isEqualTo(target.getTitle());
        assertThat(expected.getContent()).isEqualTo(target.getContent());
    }

    @Test
    @Transactional
    void create_실패() {
        //given
        String title = "userA";
        String content = "1111";
        ArticleForm form = new ArticleForm(null, title, content);
        Article expected = new Article(4L, null, null);
        //when
        Article target = articleService.create(form);
        //then
        assertThat(expected.getTitle()).isNotEqualTo(target.getTitle());
        assertThat(expected.getContent()).isNotEqualTo(target.getContent());
    }

    @Test
    @Transactional
    void update_성공() {
        //given
        Long id = 4L;
        String title = "userA";
        String content = "1111";
        ArticleForm articleForm = new ArticleForm(null, title, content);
        ArticleForm updatedForm = new ArticleForm(id, "updated", content);
        //when
        Article article = articleService.create(articleForm);
        Article target = articleService.update(id, updatedForm);
        //then
        assertThat(updatedForm.getTitle()).isEqualTo(target.getTitle());
    }

    @Test
    @Transactional
    void delete_성공() {
        //given
        Long id = 4L;
        String title = "userA";
        String content = "1111";
        ArticleForm articleForm = new ArticleForm(null, title, content);
        //when
        Article article = articleService.create(articleForm);
        Article target = articleService.delete(id);
        //then
        assertThat(article.getTitle()).isEqualTo(target.getTitle());
    }

    @Test
    @Transactional
    void delete_실패() {
        //given
        Long id = 4L;
        String title = "userA";
        String content = "1111";
        ArticleForm articleForm = new ArticleForm(null, title, content);
        //when
        Article article = articleService.create(articleForm);
        Article target = articleService.delete(3L);
        //then
        assertThat(article.getTitle()).isNotEqualTo(target.getTitle());
    }
}