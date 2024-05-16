package com.queerxdisasster.virtualbookshelf.controller;

import com.queerxdisasster.virtualbookshelf.entity.SharedBookshelfBook;
import com.queerxdisasster.virtualbookshelf.service.SharedBookshelfBookService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/shared-bookshelf-books")
@RequiredArgsConstructor
public class SharedBookshelfBookController {
    private final SharedBookshelfBookService sharedBookshelfBookService;

    @GetMapping("/shared-bookshelves/{sharedBookshelfId}")
    public ResponseEntity<List<SharedBookshelfBook>> getBooksBySharedBookshelfId(@PathVariable Long sharedBookshelfId) {
        return ResponseEntity.ok(sharedBookshelfBookService.findBySharedBookshelfId(sharedBookshelfId));
    }

    @GetMapping("/books/{bookId}")
    public ResponseEntity<List<SharedBookshelfBook>> getSharedBookshelvesByBookId(@PathVariable Long bookId) {
        return ResponseEntity.ok(sharedBookshelfBookService.findByBookId(bookId));
    }

    @PostMapping
    public ResponseEntity<SharedBookshelfBook> addSharedBookshelfBook(@RequestBody SharedBookshelfBook sharedBookshelfBook) {
        return ResponseEntity.ok(sharedBookshelfBookService.save(sharedBookshelfBook));
    }
}
