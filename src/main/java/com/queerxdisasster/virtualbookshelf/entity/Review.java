package com.queerxdisasster.virtualbookshelf.entity;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;
@Entity
@Data

public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String comment;
    private double rating;

    @ManyToOne
    private Book book;

    @ManyToOne
    private User user;

    // Getters and Setters
}