package Spike.springboot.first.service;


import Spike.springboot.first.dto.CommentDto;
import Spike.springboot.first.entity.Comment;
import Spike.springboot.first.entity.Member;
import Spike.springboot.first.repository.CommentRepository;

import Spike.springboot.first.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;
    private final MemberRepository memberRepository;

    public List<CommentDto> findCommentsById(Long userId) {
        return commentRepository.findById(userId).stream().map(comment -> CommentDto.createCommentDto(comment)).collect(Collectors.toList());
    }

    @Transactional
    public CommentDto create(Long memberId, CommentDto dto) {
        Member member = memberRepository.findById(memberId).orElseThrow(() -> new IllegalArgumentException("잘못된 게시글 id 요청입니다."));
        // dto -> entity 변환
        Comment comment = Comment.createComment(dto, member);
        // 댓글 저장
        Comment created = commentRepository.save(comment);
        // entity -> dto 변환
        return CommentDto.createCommentDto(created);

    }


    @Transactional
    public CommentDto update(Long commentId, CommentDto dto) {
        Comment target = commentRepository.findById(commentId).orElseThrow(() -> new IllegalArgumentException("잘못된 게시글 id 요청입니다."));
        target.update(dto);
        Comment save = commentRepository.save(target);
        return CommentDto.createCommentDto(save);
    }
}
