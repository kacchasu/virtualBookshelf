package com.queerxdisasster.virtualbookshelf.security;

import com.queerxdisasster.virtualbookshelf.entity.User;
import com.queerxdisasster.virtualbookshelf.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;


@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public User registerUser(UserRegistrationDto registrationDto) {
        User newUser = new User();
        newUser.setUsername(registrationDto.getUsername());
        newUser.setPassword(passwordEncoder.encode(registrationDto.getPasswordHash()));
        newUser.setEmail(registrationDto.getEmail());

        return userRepository.save(newUser);
    }

}
