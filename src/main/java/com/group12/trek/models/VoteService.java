package com.group12.trek.models;

import java.util.Optional;

import org.springframework.stereotype.Service;

@Service
public class VoteService {
    private final VoteRepository voteRepository;

    public VoteService(VoteRepository voteRepository) {
        this.voteRepository = voteRepository;
    }

    public Optional<Vote> findVoteByUserNameAndPostId(String userName, Long postId) {
        return voteRepository.findByUserNameAndPostId(userName, postId);
    }

    public long deleteVoteByUserNameAndPostId(String userName, Long postId) {
        return voteRepository.deleteByUserNameAndPostId(userName, postId);
    }

    public Vote saveVote(String userName, Long postId) {
        Vote newVote = new Vote();
        newVote.setUserName(userName);
        newVote.setPostId(postId);
        return voteRepository.save(newVote);
    }

    public boolean hasVoted(String userName, Long postId){
        return voteRepository.findByUserNameAndPostId(userName, postId).isPresent();
    }
}
