package com.group12.trek.models;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {
    List<Post> findByPlaceGeohash(String placeGeohash);
    List<Post> findByUser(String user);
}