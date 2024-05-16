package com.queerxdisasster.virtualbookshelf.controller;

import com.queerxdisasster.virtualbookshelf.dto.LoginDto;
import com.queerxdisasster.virtualbookshelf.dto.UserRegistrationDto;
import com.queerxdisasster.virtualbookshelf.entity.User;
import com.queerxdisasster.virtualbookshelf.repository.UserRepository;
import com.queerxdisasster.virtualbookshelf.security.*;
import com.queerxdisasster.virtualbookshelf.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users")
@CrossOrigin(origins = "http://localhost:3000")
public class UserController {
    private final UserService userService;
    private final JwtTokenService jwtTokenService;
    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody UserRegistrationDto registrationDto) {
        User registeredUser = userService.registerUser(registrationDto);
        String token = jwtTokenService.createToken(registeredUser.getUsername());

        Map<String, Object> response = new HashMap<>();
        response.put("user", registeredUser);
        response.put("token", token);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }


    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody LoginDto loginDto) {
        Authentication authentication = authenticateUser(loginDto.getUsername(), loginDto.getPassword());
        SecurityContextHolder.getContext().setAuthentication(authentication);

        String token = jwtTokenService.createToken(loginDto.getUsername());
        CustomUser loggedInUser = (CustomUser) authentication.getPrincipal();

        Map<String, Object> response = new HashMap<>();
        response.put("user", loggedInUser);
        response.put("token", token);

        return ResponseEntity.ok(response);
    }

    @PostMapping("/logout")
    public ResponseEntity<?> logoutUser(Principal principal) {
        String username = principal.getName();
        User user = userRepository.findByUsername(username).orElse(null);
        if (user != null) {
            userRepository.save(user);
            return ResponseEntity.ok("successful logout");
        }
        return ResponseEntity.ok("user not found");
    }

    @PostMapping("/validateToken")
    public ResponseEntity<?> validateToken(@RequestBody Map<String, String> request) {
        String token = request.get("token");
        System.out.println("token=" + token);
        try {
            if (jwtTokenService.validateToken(token))
                return ResponseEntity.ok().build();
            else
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

    private Authentication authenticateUser(String username, String password) {
        return authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
    }


}
