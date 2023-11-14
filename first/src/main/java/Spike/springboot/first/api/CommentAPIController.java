package Spike.springboot.first.api;

import Spike.springboot.first.dto.CommentDto;
import Spike.springboot.first.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/member")
@RequiredArgsConstructor
public class CommentAPIController {
    private final CommentService commentService;

    // 댓글 조회
    @GetMapping("/{userId}/comments")
    public ResponseEntity<List<CommentDto>> comments(@PathVariable Long userId) {
        List<CommentDto> dtos = commentService.findCommentsById(userId);
        return ResponseEntity.status(HttpStatus.OK).body(dtos);
    }

    // 댓글 생성
    @PostMapping("/{memberId}/comments")
    public ResponseEntity<CommentDto> create(@PathVariable Long memberId, @RequestBody CommentDto dto) {
        CommentDto commentDto = commentService.create(memberId, dto);
        return ResponseEntity.status(HttpStatus.OK).body(commentDto);
    }

    // 댓글 수정
    @PatchMapping("/{commentId}/comments")
    public ResponseEntity<CommentDto> update(@PathVariable Long commentId, @RequestBody CommentDto dto) {
        CommentDto updatedDto = commentService.update(commentId, dto);
        return ResponseEntity.status(HttpStatus.OK).body(updatedDto);
    }
}
