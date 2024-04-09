package com.group12.trek.models;

import java.util.List;
import com.group12.trek.models.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment,Long> {

    Comment getCommentById(long Id);
    // List<Comment> getCommentByPostId(long PostId);
    long deleteById(long Id); 
} 