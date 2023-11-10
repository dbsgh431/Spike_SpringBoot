package Spike.springboot.first.entity;

import Spike.springboot.first.dto.CommentDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String username;
    @Column
    private String body;
    @JoinColumn(name = "member_id")
    @ManyToOne
    private Member member;

    public static Comment createComment(CommentDto dto, Member member) {
        if (dto.getId() != null) {
            throw new IllegalArgumentException("이미 존재하는 댓글 id 입니다.");
        }

        if (dto.getMemberId() != member.getId()) {
            throw new IllegalArgumentException("유효하지 않은 게시글 id 입니다.");
        }
        return new Comment(dto.getId(), dto.getUsername(), dto.getBody(), member);
    }

}
