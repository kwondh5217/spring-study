package com.example.demojpa3.post;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Builder
@Getter @Setter
public class PostResponse {

    private String title;
    private String content;
    private LocalDate createdDate;

    public Post toEntity(){
        return Post.builder()
                .title(title)
                .content(content)
                .createdDate(createdDate)
                .build();
    }
}
