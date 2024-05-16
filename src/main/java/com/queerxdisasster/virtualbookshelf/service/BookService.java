package com.queerxdisasster.virtualbookshelf.service;

import com.queerxdisasster.virtualbookshelf.dto.BookDto;
import com.queerxdisasster.virtualbookshelf.dto.CategoryDto;
import com.queerxdisasster.virtualbookshelf.entity.Book;
import com.queerxdisasster.virtualbookshelf.entity.BookCategory;
import com.queerxdisasster.virtualbookshelf.entity.Category;
import com.queerxdisasster.virtualbookshelf.repository.BookCategoryRepository;
import com.queerxdisasster.virtualbookshelf.repository.BookRepository;
import com.queerxdisasster.virtualbookshelf.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BookService {
    private final BookRepository bookRepository;
    private final CategoryRepository categoryRepository;
    private final BookCategoryRepository bookCategoryRepository;

    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    public Optional<Book> getBookById(Long id) {
        return bookRepository.findById(id);
    }

    public List<Book> getBooksByCategoryId(Long categoryId) {
        List<BookCategory> bookCategories = bookCategoryRepository.findByCategoryId(categoryId);
        return bookCategories.stream()
                .map(BookCategory::getBook)
                .distinct()
                .collect(Collectors.toList());
    }

    @Transactional
    public Book saveBook(BookDto bookDto) {
        Set<Category> categories = new HashSet<>();
        if (bookDto.getCategories() != null) {
            for (CategoryDto categoryDto : bookDto.getCategories()) {
                Category existingCategory = categoryRepository.findByName(categoryDto.getName())
                        .orElseGet(() -> {
                            Category newCategory = new Category();
                            newCategory.setName(categoryDto.getName());
                            return categoryRepository.save(newCategory);
                        });
                categories.add(existingCategory);
            }
        }

        Book book = new Book();
        book.setTitle(bookDto.getTitle());
        book.setAuthor(bookDto.getAuthor());
        book.setDescription(bookDto.getDescription());
        book.setImageUrl(bookDto.getImageUrl());
        Book savedBook = bookRepository.save(book);

        for (Category category : categories) {
            BookCategory bookCategory = new BookCategory();
            bookCategory.setBook(savedBook);
            bookCategory.setCategory(category);
            bookCategoryRepository.save(bookCategory);
        }

        return savedBook;
    }
}
