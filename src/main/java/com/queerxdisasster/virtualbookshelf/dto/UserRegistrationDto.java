package com.queerxdisasster.virtualbookshelf.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class UserRegistrationDto {
    private String username;
    private String password;
    private String email;
}
