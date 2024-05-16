package com.queerxdisasster.virtualbookshelf.dto;

import lombok.Data;

import java.util.Set;

@Data
public class BookDto {
    private Long id;
    private String title;
    private String author;
    private String imageUrl; // Link to the cover image
    private String description; // Description of the book
    private Set<CategoryDto> categories;
}
