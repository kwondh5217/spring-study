package com.example.demojpa3;

import com.example.demojpa3.post.SimpleMyRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(repositoryBaseClass = SimpleMyRepository.class)
public class Application {

    ApplicationContext applicationContext;
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}
