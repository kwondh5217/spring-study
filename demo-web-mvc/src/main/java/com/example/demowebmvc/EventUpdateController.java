package com.example.demowebmvc;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(
        consumes = MediaType.APPLICATION_JSON_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE
)
public class EventUpdateController {

    @PutMapping("events/{id}")
    @ResponseBody
    public String updateEvents(@PathVariable int id){
        return "events";
    }
    @PostMapping("/events")
    @ResponseBody
    public String postEvents(){
        return "events";
    }

}
