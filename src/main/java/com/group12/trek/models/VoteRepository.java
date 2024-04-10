package com.group12.trek.models;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface VoteRepository extends JpaRepository<Vote, Long> {
    Optional<Vote> findByUserNameAndPostId(String userName, Long postId);
    long deleteByUserNameAndPostId(String userName, Long postId);
}
