package Spike.springboot.first.service;


import Spike.springboot.first.dto.CommentDto;
import Spike.springboot.first.repository.CommentRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;

    public List<CommentDto> findCommentsById(Long userId) {
        return commentRepository.findById(userId)
                .stream()
                .map(comment -> CommentDto.commentDto(
                        comment.getId(),
                        comment.getUsername(),
                        comment.getBody(),
                        comment.getMember().getId()))
                .collect(Collectors.toList());
    }
}
