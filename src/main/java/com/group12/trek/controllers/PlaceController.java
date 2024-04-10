package com.group12.trek.controllers;

import com.group12.trek.models.*;

import jakarta.servlet.http.HttpSession;

import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.time.LocalDate;
import java.time.ZoneId;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class PlaceController {
    private final PlaceService placeService;
    private final PostService postService;

    @Autowired
    VoteService voteService;

    @Autowired
    private UserService userService;

    public PlaceController(PlaceService placeService, PostService postService) {
        this.placeService = placeService;
        this.postService = postService;
    }

    @GetMapping("/")
    public String listPlaces(Model model) {
        List<Place> places = placeService.findAll();
        model.addAttribute("places", places);

        return "index";
    }

    @PostMapping("/addPlace")
    public String addPlace(@RequestParam String geohash, @RequestParam String name, @RequestParam String description) {
        Place place = new Place();
        place.setGeohash(geohash);
        place.setName(name);
        place.setDescription(description);
        placeService.save(place);
        return "redirect:/";
    }

    @GetMapping("/place")
    public String viewPlace(@RequestParam String placeGeohash, Model model, HttpSession session) {
        Object loggedInUser = session.getAttribute("user");

        List<Post> posts = postService.findByPlaceGeohash(placeGeohash);
        posts.sort(Comparator.comparing(Post::getPostDate).reversed()
            .thenComparing(Comparator.comparing(Post::getVote).reversed()));

        Map<LocalDate, List<Post>> postsByDate = posts.stream()
        .collect(Collectors.groupingBy(
            post -> post.getPostDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate(), 
            LinkedHashMap::new, 
            Collectors.toList()
        ));
        model.addAttribute("postsByDate", postsByDate);
        model.addAttribute("placeGeohash", placeGeohash);
    
        placeService.findByGeohash(placeGeohash).ifPresent(place -> {
            model.addAttribute("place", place);
        });

        if (loggedInUser != null) {
            User user = (User) loggedInUser;
            String username = user.getUsername();
            Map<Long, Boolean> votesMap = new HashMap<>();
            posts.forEach(post -> votesMap.put(post.getId(),
                    voteService.hasVoted(username, post.getId())));
            model.addAttribute("votesMap", votesMap);
        }

        return "place";
    }

    @GetMapping("/profile")
    public String visitProfile(@RequestParam String username, Model model, HttpSession session) {
            
            User user_passed_in = userService.findUserByUsername(username);

            Map<Long, Boolean> votesMap = new HashMap<>();

            List<Post> posts = postService.findByUsername(username);

            posts.sort(Comparator.comparingInt(Post::getVote).reversed());
            model.addAttribute("posts", posts);

            posts.forEach(post -> votesMap.put(post.getId(),
                voteService.hasVoted(username, post.getId())));

            model.addAttribute("votesMap", votesMap);
            model.addAttribute("user", user_passed_in);

        return "profile";
    }

    @PostMapping("/addPost")
    public String addPost(@RequestParam String placeGeohash, @RequestParam String title, @RequestParam String content,
    HttpSession session) {
        Object loggedInUser = session.getAttribute("user");
    
        if (loggedInUser == null) {
            return "redirect:/login";
        }
    
        User user = (User) loggedInUser;
    
        Post post = new Post();
        post.setPlaceGeohash(placeGeohash);
        post.setUser(user.getUsername());
        post.setTitle(title);
        post.setContent(content);
    
        long currentTimeMillis = System.currentTimeMillis();
        post.setTimestamp(currentTimeMillis / 1000);
        post.setPostDate(new Date(currentTimeMillis));
    
        postService.save(post);
        return "redirect:/place?placeGeohash=" + placeGeohash;
    }
}