package com.example.demojpa3.post;

import org.springframework.context.ApplicationListener;
import org.springframework.context.event.EventListener;

public class PostListener{

    @EventListener
    public void onApplicationEvent(PostPublishedEvent event) {
        System.out.println("=============");
        System.out.println(event.getPost() + "is published");
        System.out.println("=============");
    }
}
