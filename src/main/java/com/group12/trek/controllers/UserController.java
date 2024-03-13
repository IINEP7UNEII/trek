package com.group12.trek.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller; // Change from @RestController to @Controller
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
    public String loginPage() {
        return "login";
    }

    @GetMapping("/register")
    public String registerPage() {
        return "register";
    }

    @PostMapping("/users/login")
    public ModelAndView loginUser(@RequestParam String username, @RequestParam String password, HttpSession session) {
        User user = userService.findUserByUsername(username);

        if (user != null && user.getPassword().equals(password)) {
            session.setAttribute("user", user);
            return new ModelAndView("redirect:/");
        } else {
            ModelAndView modelAndView = new ModelAndView("login");
            modelAndView.addObject("error", "Invalid username or password.");
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
}