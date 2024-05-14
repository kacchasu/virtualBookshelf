package com.queerxdisasster.virtualbookshelf.controller;

import com.queerxdisasster.virtualbookshelf.entity.Book;
import com.queerxdisasster.virtualbookshelf.entity.User;
import com.queerxdisasster.virtualbookshelf.repository.UserRepository;
import com.queerxdisasster.virtualbookshelf.security.UserService;
import com.queerxdisasster.virtualbookshelf.service.BookService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/books")
public class BookController {
    private final BookService bookService;
    private final UserService userService;

    public BookController(BookService bookService, UserService userService) {
        this.bookService = bookService;
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<Book> addBook(@RequestBody Book book) {
        Book savedBook = bookService.save(book);
        return new ResponseEntity<>(savedBook, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Book> getBook(@PathVariable Long id) {
        Book book = bookService.findById(id);
        return ResponseEntity.ok(book);
    }
    @GetMapping("/user/books")
    public ResponseEntity<List<Book>> getUserBooks(Authentication authentication) {
        String username = authentication.getName();
        User user = userService.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
        List<Book> books = bookService.findAllByUserId(user.getId());
        return ResponseEntity.ok(books);
    }


    // Additional endpoints as needed
}
