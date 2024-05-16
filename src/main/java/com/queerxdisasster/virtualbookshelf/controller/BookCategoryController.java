package com.queerxdisasster.virtualbookshelf.controller;

import com.queerxdisasster.virtualbookshelf.entity.BookCategory;
import com.queerxdisasster.virtualbookshelf.service.BookCategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/book-categories")
@RequiredArgsConstructor
public class BookCategoryController {
    private final BookCategoryService bookCategoryService;

    @GetMapping("/books/{bookId}")
    public ResponseEntity<List<BookCategory>> getCategoriesByBookId(@PathVariable Long bookId) {
        return ResponseEntity.ok(bookCategoryService.findByBookId(bookId));
    }

    @GetMapping("/categories/{categoryId}")
    public ResponseEntity<List<BookCategory>> getBooksByCategoryId(@PathVariable Long categoryId) {
        return ResponseEntity.ok(bookCategoryService.findByCategoryId(categoryId));
    }

    @PostMapping
    public ResponseEntity<BookCategory> addBookCategory(@RequestBody BookCategory bookCategory) {
        return ResponseEntity.ok(bookCategoryService.save(bookCategory));
    }
}
