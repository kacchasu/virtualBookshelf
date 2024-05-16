package com.queerxdisasster.virtualbookshelf.controller;

import com.queerxdisasster.virtualbookshelf.dto.BookDto;
import com.queerxdisasster.virtualbookshelf.entity.Book;
import com.queerxdisasster.virtualbookshelf.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/books")
@CrossOrigin(origins = "http://localhost:3000")
public class BookController {
    private final BookService bookService;

    @GetMapping
    public List<Book> getAllBooks() {
        return bookService.getAllBooks();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Book> getBookById(@PathVariable Long id) {
        return bookService.getBookById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/category/{categoryId}")
    public List<Book> getBooksByCategoryId(@PathVariable Long categoryId) {
        return bookService.getBooksByCategoryId(categoryId);
    }

    @PostMapping
    public Book saveBook(@RequestBody BookDto bookDto) {
        return bookService.saveBook(bookDto);
    }
}
