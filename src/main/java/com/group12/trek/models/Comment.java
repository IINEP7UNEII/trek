package com.group12.trek.models;

import java.util.Date;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "comments")
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "user_name") 
    private String username;
    @Column(name = "comment_date")
    private Date CommentDate;
    @Column(name = "post_id")
    private Long PostId;
    @Column(name = "content")
    private String content;

    public Comment(){

    }

    public Comment(String username, Date CommentDate, Long PostId, String content){
        this.username = username;
        this.CommentDate = CommentDate;
        this.PostId = PostId;
        this.content = content;
    }
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Long getPostId() {
        return PostId;
    }

    public void setPostId(Long PostId) {
        this.PostId = PostId;
    }


    
}
