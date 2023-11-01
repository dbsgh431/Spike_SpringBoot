package Spike.springboot.first.repository;

import Spike.springboot.first.entity.Comment;
import Spike.springboot.first.entity.Member;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

//@DataJpaTest
@SpringBootTest //DB 저장 대신 빈 주입 후 메모리에 저장하는 방식으로 더미데이터를 설계했으므로 스프링 부트를 통해 테스트
class CommentRepositoryTest {

    @Autowired CommentRepository commentRepository;

    @Test
    @DisplayName("특정 멤버가 남긴 모든 댓글 확인")
    void findByMemberId() {
        // 입력 데이터
        Long id = 2L; //조회할 id
        // 실제 데이터
        List<Comment> comments = commentRepository.findByMemberId(id);
        // 예상데이터
        Member member = new Member(2L, "bbb@naver.com", "3434");
        Comment c1 = new Comment(2L, "벵제마", "잘가세요", member);
        Comment c2 = new Comment(3L, "누가바", "잘가요", member);
        List<Comment> expected = Arrays.asList(c1, c2);
        // 결과
        assertEquals(expected.toString(), comments.toString());

    }

    @Test
    @DisplayName("특정 유저가 남긴 모든 댓글 확인")
    void findByUsername() {
        // 입력 데이터
        String username = "누가바";
        // 실제 데이터
        List<Comment> byUsername = commentRepository.findByUsername(username);
        // 예상 데이터
        Member member1 = new Member(1L, "aaa@naver.com", "1234");
        Member member2 = new Member(2L, "bbb@naver.com", "3434");
        Comment c1 = new Comment(1L, "누가바", "안녕하세요", member1);
        Comment c2 = new Comment(3L, "누가바", "잘가요", member2);
        List<Comment> expected = Arrays.asList(c1, c2);
        // 결과
        assertEquals(expected.toString(), byUsername.toString());
    }
}