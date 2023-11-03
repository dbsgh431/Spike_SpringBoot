package Spike.springboot.first.api;

import Spike.springboot.first.dto.CommentDto;
import Spike.springboot.first.repository.CommentRepository;
import Spike.springboot.first.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class CommentAPIController {
    private final CommentService commentService;

    @GetMapping("/api/member/{userId}/comments")
    public ResponseEntity<List<CommentDto>> comments(@PathVariable Long userId) {
        List<CommentDto> dtos = commentService.findCommentsById(userId);
        return ResponseEntity.status(HttpStatus.OK).body(dtos);
    }
}
