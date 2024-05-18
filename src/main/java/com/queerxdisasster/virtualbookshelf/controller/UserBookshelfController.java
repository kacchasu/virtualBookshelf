package com.queerxdisasster.virtualbookshelf.controller;

import com.queerxdisasster.virtualbookshelf.entity.SharedBookshelfBook;
import com.queerxdisasster.virtualbookshelf.entity.UserBookshelf;
import com.queerxdisasster.virtualbookshelf.service.SharedBookshelfBookService;
import com.queerxdisasster.virtualbookshelf.service.UserBookshelfService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user-bookshelf")
@RequiredArgsConstructor
public class UserBookshelfController {
    private final UserBookshelfService userBookshelfService;

    @GetMapping("/{userId}")
    public ResponseEntity<List<UserBookshelf>> getUserBookshelfByUserId(@PathVariable Long userId) {
        return ResponseEntity.ok(userBookshelfService.findByUserId(userId));
    }
    @GetMapping("/bookshelf-info/{bookshelfId}")
    public ResponseEntity<List<UserBookshelf>> getUserBookshelfByBookshelfId(@PathVariable Long bookshelfId) {
        return ResponseEntity.ok(userBookshelfService.findByBookshelfId(bookshelfId));
    }
}