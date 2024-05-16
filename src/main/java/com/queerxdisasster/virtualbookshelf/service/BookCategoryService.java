package com.queerxdisasster.virtualbookshelf.service;

import com.queerxdisasster.virtualbookshelf.entity.BookCategory;
import com.queerxdisasster.virtualbookshelf.repository.BookCategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BookCategoryService {
    private final BookCategoryRepository bookCategoryRepository;

    public List<BookCategory> findByBookId(Long bookId) {
        return bookCategoryRepository.findByBookId(bookId);
    }

    public List<BookCategory> findByCategoryId(Long categoryId) {
        return bookCategoryRepository.findByCategoryId(categoryId);
    }

    public BookCategory save(BookCategory bookCategory) {
        return bookCategoryRepository.save(bookCategory);
    }
}