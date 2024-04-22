package com.example.demojpa3.post;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class PostService {

    private final PostRepository postRepository;

    @Transactional(readOnly = true)
    public PostResponse findById(Long id){
        Post post = postRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Not Found"));
        return post.toDto();
    }


}
