package com.queerxdisasster.virtualbookshelf.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;
@Entity
@Data


public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    @OneToMany(mappedBy = "category")
    private List<Book> books;

    // Getters and Setters
}