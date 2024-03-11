package com.group12.trek.models;

import com.group12.trek.models.User;
import com.group12.trek.models.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService 
{
    private final UserRepository userRepository;
    
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    
    public List<User> findAll() {
        return userRepository.findAll();
    }
    
    public User save(User user) {
        return userRepository.save(user);
    }

    public Optional<User> findByGeohash(int uid) {
        return userRepository.findByUid(uid);
    }
}
