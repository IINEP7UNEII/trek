package com.group12.trek.models;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment,Long> {

    Comment getCommentById(long Id);
    long deleteById(long Id); 
} 