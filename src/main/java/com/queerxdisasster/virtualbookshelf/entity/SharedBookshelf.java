package com.queerxdisasster.virtualbookshelf.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.HashSet;
import java.util.Set;

@Data
@Entity
public class SharedBookshelf {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @OneToMany(mappedBy = "sharedBookshelf", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<SharedBookshelfBook> sharedBookshelfBooks = new HashSet<>();

    @OneToMany(mappedBy = "sharedBookshelf", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<UserBookshelf> userBookshelves = new HashSet<>();
}
