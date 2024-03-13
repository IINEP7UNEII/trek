package com.group12.trek.models;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    
    User findByUsername(String username);
}
