package com.group12.trek.models;

<<<<<<< Updated upstream
import java.util.List;
import com.group12.trek.models.Comment;
=======
>>>>>>> Stashed changes
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment,Long> {

    Comment getCommentById(long Id);
<<<<<<< Updated upstream
    // List<Comment> getCommentByPostId(long PostId);
=======
>>>>>>> Stashed changes
    long deleteById(long Id); 
} 