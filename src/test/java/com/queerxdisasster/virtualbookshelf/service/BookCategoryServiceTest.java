package com.queerxdisasster.virtualbookshelf.service;

import com.queerxdisasster.virtualbookshelf.entity.BookCategory;
import com.queerxdisasster.virtualbookshelf.repository.BookCategoryRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class BookCategoryServiceTest {

    @Mock
    private BookCategoryRepository bookCategoryRepository;

    @InjectMocks
    private BookCategoryService bookCategoryService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void findByBookId() {
        Long bookId = 1L;
        BookCategory bookCategory1 = new BookCategory();
        bookCategory1.setId(1L);
        BookCategory bookCategory2 = new BookCategory();
        bookCategory2.setId(2L);
        List<BookCategory> bookCategories = Arrays.asList(bookCategory1, bookCategory2);

        when(bookCategoryRepository.findByBookId(bookId)).thenReturn(bookCategories);

        List<BookCategory> result = bookCategoryService.findByBookId(bookId);

        assertEquals(2, result.size());
        assertEquals(bookCategory1.getId(), result.get(0).getId());
        assertEquals(bookCategory2.getId(), result.get(1).getId());
        verify(bookCategoryRepository, times(1)).findByBookId(bookId);
    }

    @Test
    void findByCategoryId() {
        Long categoryId = 1L;
        BookCategory bookCategory1 = new BookCategory();
        bookCategory1.setId(1L);
        BookCategory bookCategory2 = new BookCategory();
        bookCategory2.setId(2L);
        List<BookCategory> bookCategories = Arrays.asList(bookCategory1, bookCategory2);

        when(bookCategoryRepository.findByCategoryId(categoryId)).thenReturn(bookCategories);

        List<BookCategory> result = bookCategoryService.findByCategoryId(categoryId);

        assertEquals(2, result.size());
        assertEquals(bookCategory1.getId(), result.get(0).getId());
        assertEquals(bookCategory2.getId(), result.get(1).getId());
        verify(bookCategoryRepository, times(1)).findByCategoryId(categoryId);
    }

    @Test
    void save() {
        BookCategory bookCategory = new BookCategory();
        bookCategory.setId(1L);

        when(bookCategoryRepository.save(bookCategory)).thenReturn(bookCategory);

        BookCategory result = bookCategoryService.save(bookCategory);

        assertEquals(bookCategory.getId(), result.getId());
        verify(bookCategoryRepository, times(1)).save(bookCategory);
    }
}
