package Spike.springboot.first.service;

import Spike.springboot.first.dto.CommentDto;
import Spike.springboot.first.entity.Article;
import Spike.springboot.first.entity.Comment;
import Spike.springboot.first.repository.ArticleRepository;
import Spike.springboot.first.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CommentService {

    @Autowired
    CommentRepository commentRepository;
    @Autowired
    ArticleRepository articleRepository;


    public List<CommentDto> comments(Long articleId) {
        // 댓글 조회
        List<Comment> comments = commentRepository.findByArticleId(articleId);

//        // 엔티티 -> dto 변환(스트림 대체)
//        List<CommentDto> commentDtos = new ArrayList<CommentDto>();
//        for (Comment comment : comments) {
//            CommentDto dto = CommentDto.createCommentDto(comment);
//            commentDtos.add(dto);
//        }
        // 결과 반환
        return commentRepository.findByArticleId(articleId)
                .stream()
                .map(comment -> CommentDto.createCommentDto(comment))
                .collect(Collectors.toList());

    }

    @Transactional
    public CommentDto create(Long articleId, CommentDto dto) {
        // 1. 게시글 조회 및 예외 처리
        Article article = articleRepository.findById(articleId).orElseThrow(() -> new IllegalArgumentException("댓글 생성 실패!" + "대상 게시글이 존재하지 않습니다."));
        // 2. 댓글 엔티티 생성
        Comment comment = Comment.createComment(dto, article);
        // 3. 댓글 엔티티 저장
        Comment created = commentRepository.save(comment);
        // 4. DTO로 변환 후 반환
        return CommentDto.createCommentDto(comment);

    }
}
