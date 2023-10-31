package Spike.springboot.first.repository;

import Spike.springboot.first.entity.Article;
import Spike.springboot.first.entity.Comment;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class CommentRepositoryTest {

    @Autowired
    CommentRepository commentRepository;
    @Test
    @DisplayName("특정 게시글의 모든 댓글 조회")
    void findByArticleId() {
        /* 1번 게시글의 모든 댓글 조회 */
        {
            // 1. 입력 데이터 준비
            Long articleId = 1L;
            // 2. 실제 데이터
            List<Comment> comments = commentRepository.findByArticleId(articleId);
            // 3. 예상 데이터
            Article article = new Article(1L, "직업", "답변");
            List<Comment> expected = Arrays.asList();
            // 4. 비교 및 검증
            assertEquals(expected.toString(), comments.toString(), "1번 글의 댓글이 없습니다.");

        }
        /* 3번 게시글의 모든 댓글 조회 */
        {
            // 1. 입력 데이터 준비
            Long articleId = 4L;
            // 2. 실제 데이터
            List<Comment> comments = commentRepository.findByArticleId(articleId);
            // 3. 예상 데이터
            Article article = new Article(4L, "직업", "답변");
            Comment a = new Comment(1L, article, "헛개수", "군인");
            Comment b = new Comment(2L, article, "밀리탕", "축구선수");
            List<Comment> expected = Arrays.asList(a, b);
            assertEquals(expected.toString(), comments.toString(), "4번 글의 모든 댓글 출력");

        }
    }

    @Test
    @DisplayName("특정 이용자의 모든 댓글 조회")
    void findByNickname() {
        // 1. 입력 데이터 준비
        String nickName = "헛개수";
        // 2. 실제 데이터
        List<Comment> comments = commentRepository.findByNickname(nickName);
        // 3. 예상 데이터
        Comment a = new Comment(1L, new Article(4L, "직업", "답변"),"헛개수", "군인");
        Comment b = new Comment(3L, new Article(5L, "나이", "답변"),"헛개수", "22");
        Comment c = new Comment(5L, new Article(6L, "성별", "답변"),"헛개수", "남");
        // 4. 비교 및 검증
        List<Comment> expected = Arrays.asList(a, b, c);

        System.out.println(expected.toString());

        assertEquals(expected.toString(), comments.toString(),"헛개수님의 댓글 출력");
    }
}