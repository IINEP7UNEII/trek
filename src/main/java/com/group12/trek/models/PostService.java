package com.group12.trek.models;

import com.group12.trek.models.Post;
import com.group12.trek.models.PostRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostService {
    private final PostRepository postRepository;

    public PostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    public List<Post> findByPlaceGeohash(String placeGeohash) {
        return postRepository.findByPlaceGeohash(placeGeohash);
    }

    public Post save(Post post) {
        return postRepository.save(post);
    }
}
