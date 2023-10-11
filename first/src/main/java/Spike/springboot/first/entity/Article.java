package Spike.springboot.first.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor // 기본 생성자 추가
@ToString
@Getter
public class Article {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 자동으로 id를 생성
    private Long id;
    @Column
    private String title;
    @Column
    private String content;

    public void patch(Article article) {
        if (article.title != null) {
            this.title = article.title;
        }
        if (article.content != null) {
            this.content = article.content;
        }

    }
}
