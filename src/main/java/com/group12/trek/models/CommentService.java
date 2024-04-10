package com.group12.trek.models;

import java.util.List;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class CommentService {
    private final CommentRepository commentRepository;

    public CommentService(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    public Optional<Comment> getCommentById(long Id) {
        return commentRepository.findById(Id);
    }

    public List<Comment> findAllComments(){
        return commentRepository.findAll();
    }

    public long deleteCommentById(long Id) {
        return commentRepository.deleteById(Id);
    }

    public Comment save(Comment comment) {
        return commentRepository.save(comment);
    }
}
