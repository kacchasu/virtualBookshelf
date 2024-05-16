package com.queerxdisasster.virtualbookshelf.dto;

import lombok.Data;
import java.util.Set;

@Data
public class SharedBookshelfDto {
    private String name;
    private Set<Long> bookIds;
    private Long ownerId;
}
