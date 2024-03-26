package com.group12.trek.controllers;

import com.group12.trek.models.Post;
import com.group12.trek.models.PostService;
import com.group12.trek.models.User;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class PostController {

    @Autowired
    private PostService postService;

    @PostMapping("/editPost")
    public String editPost(@RequestParam Long postId, @RequestParam String title, @RequestParam String content, HttpSession session) {
        Object loggedInUser = session.getAttribute("user");
        if (loggedInUser == null) {
            return "redirect:/login";
        }

        User user = (User) loggedInUser;
        String username = user.getUsername();
        
        Post post = postService.findById(postId).orElse(null);
        if (post != null && (post.getUser().equals(username) || username.equals("admin"))) {
            post.setTitle(title);
            post.setContent(content);
            postService.save(post);
        }
        return "redirect:/place?placeGeohash=" + post.getPlaceGeohash();
    }

    @PostMapping("/deletePost")
    public String deletePost(@RequestParam Long postId, HttpSession session) {
        Object loggedInUser = session.getAttribute("user");
        if (loggedInUser == null) {
            return "redirect:/login";
        }

        User user = (User) loggedInUser;
        String username = user.getUsername();
        
        Post post = postService.findById(postId).orElse(null);
        if (post != null && (post.getUser().equals(username) || username.equals("admin"))) {
            postService.delete(post);
        }
        return "redirect:/place?placeGeohash=" + post.getPlaceGeohash();
    }
}
