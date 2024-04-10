package com.group12.trek.controllers;
<<<<<<< Updated upstream
import com.group12.trek.models.Place;
import com.group12.trek.models.PlaceService;
import com.group12.trek.models.Post;
import com.group12.trek.models.PostService;
import com.group12.trek.models.User;
import com.group12.trek.models.UserRepository;
import com.group12.trek.models.UserService;
import com.group12.trek.models.VoteService;
import com.group12.trek.models.Comment;
import com.group12.trek.models.CommentRepository;
import com.group12.trek.models.CommentService;
import com.group12.trek.models.Post;
import com.group12.trek.models.PostService;
import com.group12.trek.models.User;
=======

import com.group12.trek.models.*;
>>>>>>> Stashed changes
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

<<<<<<< Updated upstream
import jakarta.servlet.http.HttpSession;

import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.time.LocalDate;
import java.util.Calendar;
import java.time.ZoneId;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestBody;
=======

import java.util.Date;
import java.util.List;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
>>>>>>> Stashed changes


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
