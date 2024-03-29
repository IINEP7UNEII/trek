package com.group12.trek.controllers;

import com.group12.trek.models.Place;
import com.group12.trek.models.PlaceService;
import com.group12.trek.models.Post;
import com.group12.trek.models.PostService;
import com.group12.trek.models.User;
import com.group12.trek.models.UserRepository;
import com.group12.trek.models.UserService;
import com.group12.trek.models.VoteService;

import jakarta.servlet.http.HttpSession;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class PlaceController {
    private final PlaceService placeService;
    private final PostService postService; // Declare PostService

    @Autowired
    VoteService voteService;

    @Autowired
    private UserService userService;

    // Modify the constructor to include PostService
    public PlaceController(PlaceService placeService, PostService postService) {
        this.placeService = placeService;
        this.postService = postService;
    }

    @GetMapping("/")
    public String listPlaces(Model model) {
        List<Place> places = placeService.findAll();
        model.addAttribute("places", places);

        // Add a log statement to check if places are found
        System.out.println("Places: " + places);

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
        posts.sort(Comparator.comparingInt(Post::getVote).reversed());
        model.addAttribute("posts", posts);
        model.addAttribute("placeGeohash", placeGeohash);

        placeService.findByGeohash(placeGeohash).ifPresent(place -> {
            model.addAttribute("place", place); // Add the entire place object
            // model.addAttribute("placeName", place.getName());
        });

        if (loggedInUser != null) {
            User user = (User) loggedInUser;
            String username = user.getUsername();
            Map<Long, Boolean> votesMap = new HashMap<>();
            posts.forEach(post -> votesMap.put(post.getId(),
                    voteService.hasVoted(username, post.getId())));
            // for (Post post : posts) {
            // votesMap.put(post.getId(), voteService.hasVoted(username, post.getId()));
            // }
            // for (Map.Entry<Long, Boolean> entry : votesMap.entrySet()) {
            //     System.out.println("Post ID: " + entry.getKey() + ", Has Voted: " + entry.getValue());
            // }
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
            // for (Post post : posts) {
            // votesMap.put(post.getId(), voteService.hasVoted(username, post.getId()));
            // }
            // for (Map.Entry<Long, Boolean> entry : votesMap.entrySet()) {
            //     System.out.println("Post ID: " + entry.getKey() + ", Has Voted: " + entry.getValue());
            // }
            model.addAttribute("votesMap", votesMap);
            model.addAttribute("user", user_passed_in);

        return "profile";
    }

    @PostMapping("/addPost")
    public String addPost(@RequestParam String placeGeohash, @RequestParam String title, @RequestParam String content,
            HttpSession session) {
        Object loggedInUser = session.getAttribute("user"); // Get the user from the session

        if (loggedInUser == null) {
            // Handle the case where there is no logged-in user
            return "redirect:/login";
        }

        User user = (User) loggedInUser;

        Post post = new Post();
        post.setPlaceGeohash(placeGeohash);
        post.setUser(user.getUsername());
        post.setTitle(title);
        post.setContent(content);
        // Set other properties as needed
        postService.save(post);
        return "redirect:/place?placeGeohash=" + placeGeohash;
    }

}