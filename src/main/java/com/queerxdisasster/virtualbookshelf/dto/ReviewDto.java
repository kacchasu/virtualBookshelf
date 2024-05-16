package com.queerxdisasster.virtualbookshelf.dto;

import lombok.Data;

@Data
public class ReviewDto {
    private Long bookId;
    private String comment;
    private int rating;
}
