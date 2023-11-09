package Spike.springboot.first.dto;

import Spike.springboot.first.entity.Comment;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@Getter
@NoArgsConstructor
@ToString
public class CommentDto {
    private Long id;
    private Long articleId; //댓글 부모(게시글) id
    private String nickname;
    private String body; // 댓글 내용

    public static CommentDto createCommentDto(Comment comment) {
        return new CommentDto(
                comment.getId(),
                comment.getArticle().getId(),
                comment.getNickname(),
                comment.getBody()
        );
    }

}
