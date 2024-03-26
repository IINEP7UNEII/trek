package com.group12.trek.models;

import com.group12.trek.models.Post;
import com.group12.trek.models.PostRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

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

    public Post upvotePostById(Long id) {
        Optional<Post> postOptional = postRepository.findById(id);

        if (postOptional.isPresent()) {
            Post post = postOptional.get(); 
            post.upVote(); 
            return postRepository.save(post); 
        } else {
            throw new NoSuchElementException("Post with ID " + id + " not found.");
        }
    }

    public Optional<Post> findById(Long id) {
        return postRepository.findById(id);
    }

    public void delete(Post post) {
        postRepository.delete(post);
    }

    public void deleteById(Long id) {
        postRepository.deleteById(id);
    }

    public boolean existsById(Long id) {
        return postRepository.existsById(id);
    }

}
