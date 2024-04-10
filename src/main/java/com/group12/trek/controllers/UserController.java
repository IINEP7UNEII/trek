package com.group12.trek.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.group12.trek.models.User;
import com.group12.trek.models.UserService;

import jakarta.servlet.http.HttpSession;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/login")
    public ModelAndView loginPage(@RequestParam(required = false) String placeGeohash) {
        ModelAndView modelAndView = new ModelAndView("login");
        modelAndView.addObject("placeGeohash", placeGeohash);
        return modelAndView;
    }

    @GetMapping("/register")
    public String registerPage() {
        return "register";
    }
    
    @PostMapping("/users/login")
    public ModelAndView loginUser(@RequestParam String username, @RequestParam String password, @RequestParam(required = false) String placeGeohash, HttpSession session) {
        User user = userService.findUserByUsername(username);
    
        if (user != null && user.getPassword().equals(password)) {
            session.setAttribute("user", user);
            if (placeGeohash != null && !placeGeohash.isEmpty()) {
                return new ModelAndView("redirect:/place?placeGeohash=" + placeGeohash);
            } else {
                return new ModelAndView("redirect:/");
            }
        } 
        else {
            ModelAndView modelAndView = new ModelAndView("login");
            modelAndView.addObject("error", "Invalid username or password.");
            modelAndView.addObject("placeGeohash", placeGeohash);
            return modelAndView;
        }
    }

    @PostMapping("/users/register")
    public ModelAndView registerUser(User user, RedirectAttributes redirectAttributes) {
        if (userService.findUserByUsername(user.getUsername()) != null) {
            ModelAndView modelAndView = new ModelAndView("register");
            modelAndView.addObject("error", "Username already exists.");
            return modelAndView;
        }

        userService.saveUser(user);
        redirectAttributes.addFlashAttribute("success", user.getUsername() + " was successfully registered! You can now login.");
        return new ModelAndView("redirect:/login");
    }

    @GetMapping("/logout")
    public String logoutUser(HttpSession session) {
        session.invalidate();
        return "redirect:/";
    }

    @PostMapping("/users/updateProfile")
    public ModelAndView updateProfile(@RequestParam String bio, @RequestParam String link, HttpSession session, RedirectAttributes redirectAttributes) {
        User sessionUser = (User) session.getAttribute("user");
        if (sessionUser == null) {
            return new ModelAndView("redirect:/login");
        }
    
        userService.updateUserBioAndLink(sessionUser.getUsername(), bio, link);
        redirectAttributes.addFlashAttribute("success", "Profile updated successfully!");
        
        String redirectUrl = String.format("redirect:/profile?username=%s", sessionUser.getUsername());
        return new ModelAndView(redirectUrl);
    }
}
