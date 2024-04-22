package com.example.demowebmvc;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

public class Event {
    private Long id;
    @NotBlank
    private String name;
    @Min(value = 0)
    private Integer limit;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getLimit() {
        return limit;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
    }
}
