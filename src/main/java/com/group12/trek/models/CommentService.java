package com.group12.trek.models;

import java.util.List;
import org.springframework.stereotype.Service;
import java.util.Optional;

// import org.springframework.beans.factory.annotation.Autowired;
import com.group12.trek.models.Comment;
import com.group12.trek.models.CommentRepository;
import org.springframework.stereotype.Service;
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
