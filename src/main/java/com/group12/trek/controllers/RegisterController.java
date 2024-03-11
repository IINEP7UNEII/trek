package com.group12.trek.controllers;

import com.group12.trek.models.User;
import com.group12.trek.models.UserRepository;
import com.group12.trek.models.UserService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.PostMapping;
// import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.ui.Model;

@Controller
public class RegisterController {
  @Autowired
  private UserService userService;

  @GetMapping("/creatAccount")
  public String creatAccount() {
    return "creatAccount";
  }

  @GetMapping("/checkUsername")
    @ResponseBody
    public Map<String, Boolean> checkUsername(@RequestParam String username) {
        Map<String, Boolean> response = new HashMap<>();
        boolean exists = userService.existsByUsername(username);
        response.put("exists", exists);
        return response;
    }

  @PostMapping("/register")
  public String postMethodName(@RequestParam Map<String, String> formData, Model model) {
    // add new user to database
    System.out.println("creat an account");
    String newUsername = formData.get("username");
    String newPassword = formData.get("password");
    String inputtedToken = formData.get("token");
    // ensure Username are unique
    // List<User> users = userService.findAll();
    // for (int i = 0; i < users.size(); i++) {
    //   if (users.get(i).getUserName().equals(newUsername)) {
    //     model.addAttribute("error", "Username already exists. Please choose another one.");
    //     return "creatAccount";
    //   }
    // }
    userService.save(new User(newUsername, newPassword, inputtedToken));

    return "Login"; // TODO: change directory name for Login
  }

}
