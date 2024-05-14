package com.queerxdisasster.virtualbookshelf.entity;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;
@Entity
@Data
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    public Role(String name) {
        this.name = name;
    }

    public Role() {

    }
    // Getters and Setters
}