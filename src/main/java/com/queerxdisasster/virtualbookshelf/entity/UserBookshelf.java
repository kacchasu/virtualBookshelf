package com.queerxdisasster.virtualbookshelf.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class UserBookshelf {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "shared_bookshelf_id")
    private SharedBookshelf sharedBookshelf;

    private boolean isOwner;
}