package com.example.demojpa3.post;

import com.querydsl.core.types.Predicate;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@Import(PostRepositoryTestConfig.class)
class PostRepositoryTest {

    @Autowired
    PostRepository postRepository;

    @Autowired
    ApplicationContext applicationContext;

    @Test
    public void event(){
        Post post = Post
                .builder()
                .title("test")
                .content("test content")
                .createdDate(LocalDate.now())
                .build();

        postRepository.save(post.publish());
    }

    @Test
    void crud(){
        Post post = Post
                .builder()
                .title("test")
                .content("test content")
                .createdDate(LocalDate.now())
                .build();
        postRepository.save(post);

        Predicate predicate = QPost.post.title.containsIgnoreCase("test");
        Optional<Post> one = postRepository.findOne(predicate);
        Assertions.assertThat(one).isEmpty();
    }

}