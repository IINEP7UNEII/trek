package com.group12.trek.controllers;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.group12.trek.models.UserService;

import jakarta.servlet.http.HttpServletResponse;

@Controller
public class UserController 
{
    @Autowired
    private UserService userService;

    // Show the login form
    @GetMapping("/login")
    public String showLoginForm(HttpServletResponse response) 
    {
        response.setStatus(200);
        return "login"; // Return the name of your login.html file
    }

    // Process the login attempt
    @PostMapping("/login")
    public String processLogin
    (@RequestParam Map<String, String> userLogin, HttpServletResponse response, Model model)                   
    {
        // 1. Check if username exits
        if (!userService.findByUsername(userLogin.get("username")).isEmpty())
        {
            model.addAttribute("loginNoUsernameFound", true); 
            return "login"; 
        }
         // 2. Check if password and username match
        else if (!userService.findByUsername(userLogin.get("username")).get().getPassword()
        .equals(userLogin.get("password"))) 
        {
            model.addAttribute("loginIncorrectPassword", true); 
            return "login"; 
        }
        // 3. If successful:
        else
        {
            model.addAttribute("loginSuccess", true); 
            response.setStatus(200);
            return "redirect:/index";
        }
    }
}
