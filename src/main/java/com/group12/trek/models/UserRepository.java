package com.group12.trek.models;

import com.group12.trek.models.User;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> findByUid(int uid);
    Optional<User> findByUsername(String username);
}