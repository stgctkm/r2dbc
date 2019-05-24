package com.example.r2dbc.domain;

import org.springframework.data.annotation.Id;

public class Reservation {
    @Id
    Integer id;
    String name;

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Reservation(Integer id, String name) {
        this.id = id;
        this.name = name;
    }
}
