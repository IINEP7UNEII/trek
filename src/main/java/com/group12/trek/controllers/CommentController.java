package com.group12.trek.controllers;

import com.group12.trek.models.*;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


import java.util.Date;
import java.util.List;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class CommentController {
    @Autowired
    private CommentRepository CommentRepo;

    @PostMapping("/addComment")
    public String addComment(@RequestParam String placeGeohash,  HttpSession session, @RequestParam String content, @RequestParam Long PostId) {
        Object loggedInUser = session.getAttribute("user");
        if (loggedInUser != null) {
            User user = (User) loggedInUser;
            String username = user.getUsername();
            Date currDate = new Date();
            Comment comment = new Comment(username, currDate, PostId, content);
            CommentRepo.save(comment);
            return "redirect:/place?placeGeohash=" +placeGeohash;
        }
        String username = "Error";
        Date currDate = new Date();
        Comment comment = new Comment(username, currDate, PostId, content);
        CommentRepo.save(comment);
        return "redirect:/place?placeGeohash=" +placeGeohash;
    }

    @RequestMapping(value = "comments", method = RequestMethod.GET)
    public String commentAll(Model model) {
        List<Comment> comments = CommentRepo.findAll();
        model.addAttribute("ct", comments);
        return "commentsList";
    }
    
    
}
