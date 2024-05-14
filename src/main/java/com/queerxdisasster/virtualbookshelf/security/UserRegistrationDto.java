package com.queerxdisasster.virtualbookshelf.security;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserRegistrationDto {
    private String username;
    private String passwordHash;
    private String email;
}
