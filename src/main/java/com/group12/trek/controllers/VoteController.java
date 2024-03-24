package com.group12.trek.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
// import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.group12.trek.models.PostService;
import com.group12.trek.models.VoteService;

import org.springframework.web.bind.annotation.PostMapping;
// import org.springframework.web.bind.annotation.RequestBody;

@Controller
public class VoteController {
  @Autowired
  VoteService voteService;

  @Autowired
  PostService postService;

  @PostMapping("/upvote")
  public String postMethodName(@RequestParam String placeGeohash, @RequestParam Long postId, @RequestParam String username) {
    postService.upvotePostById(postId);
    voteService.saveVote(username, postId);
    return "redirect:/place?placeGeohash=" + placeGeohash;
  }

}
