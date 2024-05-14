package com.queerxdisasster.virtualbookshelf.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity

public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String author;

    @OneToMany(mappedBy = "book")
    private List<Review> reviews;

    @ManyToOne
    private Category category;

    // Getters and Setters
}