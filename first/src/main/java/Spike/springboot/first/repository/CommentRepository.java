package Spike.springboot.first.repository;

import Spike.springboot.first.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {

    @Query(value = "SELECT * FROM comment WHERE member_id = :memberId", nativeQuery = true)
    List<Comment> findByMemberId(@Param("memberId") Long memberId);

    @Query(value = "SELECT * FROM comment WHERE username = :username", nativeQuery = true)
    List<Comment> findByUsername(@Param("username") String username);


}


