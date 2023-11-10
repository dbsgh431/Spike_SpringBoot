package Spike.springboot.first.dto;

import Spike.springboot.first.entity.Comment;
import Spike.springboot.first.entity.Member;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

@AllArgsConstructor
@ToString
@Getter
@NoArgsConstructor
@Slf4j
public class CommentDto {

    private Long id;
    private String username;
    private String body;
    private Long memberId;


    public static CommentDto createCommentDto(Comment comment) {
        return new CommentDto(comment.getId(), comment.getUsername(), comment.getBody(), comment.getMember().getId());
    }

}
