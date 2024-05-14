package com.queerxdisasster.virtualbookshelf.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;
@Data
@Entity
public class SharedBookshelf {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    @ManyToMany
    private List<User> users;

    @ManyToMany
    private List<Book> books;

    // Getters and Setters
}