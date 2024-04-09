package com.group12.trek.models;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User findUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    public User saveUser(User user) {
        return userRepository.save(user);
    }

    public void updateUserBioAndLink(String username, String bio, String link) {
        User user = userRepository.findByUsername(username);
        if (user != null) {
            user.setBio(bio);
            user.setLink(link);
            userRepository.save(user);
        }
    }
}

