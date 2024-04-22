package com.example.demojpa3.post;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.domain.AbstractAggregateRoot;

import java.time.LocalDate;

@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Post extends AbstractAggregateRoot<Post> {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Lob
    private String content;

    private LocalDate createdDate;

    public Post publish(){
        this.registerEvent(new PostPublishedEvent(this));
        return this;
    }

    public PostResponse toDto(){
        return PostResponse.builder()
                .title(this.title)
                .content(this.content)
                .createdDate(this.createdDate)
                .build();
    }

}
