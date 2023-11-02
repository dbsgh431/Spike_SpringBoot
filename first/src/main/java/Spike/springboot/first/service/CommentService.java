package Spike.springboot.first.service;

import Spike.springboot.first.dto.CommentDto;
import Spike.springboot.first.entity.Comment;
import Spike.springboot.first.repository.ArticleRepository;
import Spike.springboot.first.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
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
}
