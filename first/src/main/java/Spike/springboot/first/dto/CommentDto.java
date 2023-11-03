package Spike.springboot.first.dto;

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

    public static CommentDto commentDto(Long id, String username, String body, Long memberId) {
        return new CommentDto(id, username, body, memberId);
    }
}
