package com.example.demobootmvc;

import org.springframework.web.bind.annotation.*;

@RestController
public class SampleController {

    @GetMapping("/hello/{name}")
    public String hello(@PathVariable("name") Person person){
        return "hello " + person.getName();
    }
    @GetMapping("/hello")
    public String helloV2(@RequestParam("name") Person person){
        return "hello " + person.getName();
    }

    @GetMapping("/message")
    public String getPerson(@RequestBody String body){
        return body;
    }

    @GetMapping("/jsonMessage")
    public Person jsonMessage(@RequestBody Person person){
        return person;
    }
}
