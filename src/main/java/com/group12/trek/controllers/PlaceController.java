package com.group12.trek.controllers;

import com.group12.trek.models.Place;
import com.group12.trek.models.PlaceService;
import com.group12.trek.models.Post;
import com.group12.trek.models.PostService;
import com.group12.trek.models.User;

import jakarta.servlet.http.HttpSession;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class PlaceController {
    private final PlaceService placeService;
    private final PostService postService; // Declare PostService

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
    public String viewPlace(@RequestParam String placeGeohash, Model model) {
        List<Post> posts = postService.findByPlaceGeohash(placeGeohash);
        model.addAttribute("posts", posts);
        model.addAttribute("placeGeohash", placeGeohash);
        
        placeService.findByGeohash(placeGeohash).ifPresent(place -> {
            model.addAttribute("place", place); // Add the entire place object
            // model.addAttribute("placeName", place.getName());
        });
        
        return "place";
    }

    @PostMapping("/addPost")
    public String addPost(@RequestParam String placeGeohash, @RequestParam String title, @RequestParam String content, HttpSession session) {
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