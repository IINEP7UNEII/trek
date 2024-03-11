package com.group12.trek.models;

import com.group12.trek.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
// import java.util.List;


public interface UserRepository extends JpaRepository<User, Integer> {

  boolean existsByUsername(String username);
  
}
