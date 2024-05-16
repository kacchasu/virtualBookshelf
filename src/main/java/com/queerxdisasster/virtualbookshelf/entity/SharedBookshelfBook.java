package com.queerxdisasster.virtualbookshelf.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class SharedBookshelfBook {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "shared_bookshelf_id")
    private SharedBookshelf sharedBookshelf;

    @ManyToOne
    @JoinColumn(name = "book_id")
    private Book book;
}
