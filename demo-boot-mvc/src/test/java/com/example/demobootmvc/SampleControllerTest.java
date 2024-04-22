package com.example.demobootmvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import javax.xml.bind.Marshaller;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
class SampleControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @Test
    void hello() throws Exception {
        mockMvc.perform(get("/hello/daeho"))
                .andDo(print())
                .andExpect(content().string("hello daeho"));
    }

    @Test
    void helloV2() throws Exception {
        mockMvc.perform(get("/hello")
                        .param("name", "daeho"))
                .andDo(print())
                .andExpect(content().string("hello daeho"));
    }

    @Test
    void mobile() throws Exception {
        mockMvc.perform(get("/mobile/index.html"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(Matchers.containsString("it is mobile index page")))
                .andExpect(header().exists(HttpHeaders.CACHE_CONTROL));
    }

    @Test
    void stringMessage() throws Exception {
        mockMvc.perform(get("/message")
                .content("hello"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string("hello"));
    }

    @Test
    void jsonMessage() throws Exception {
        Person person = new Person();
        person.setId(2024L);
        person.setName("daeho");

        String jsonString = objectMapper.writeValueAsString(person);

        mockMvc.perform(get("/jsonMessage")
                .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(jsonString))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(2024))
                .andExpect(jsonPath("$.name").value("daeho"));
    }

}