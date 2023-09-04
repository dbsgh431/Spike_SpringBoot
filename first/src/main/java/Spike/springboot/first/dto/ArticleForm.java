package Spike.springboot.first.dto;

import Spike.springboot.first.entity.Article;

public class ArticleForm {

    private String title;
    private String content;

    public ArticleForm(String title, String content) {
        this.title = title;
        this.content = content;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "ArticleFrom{" + "title = '" + title +'\'' + "content = '" + content + '\'' + '}';
    }

    public Article toEntity() {
        return new Article(null, title, content);
    }
}
