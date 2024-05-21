package com.queerxdisasster.virtualbookshelf.service;

import com.queerxdisasster.virtualbookshelf.dto.BookDto;
import com.queerxdisasster.virtualbookshelf.dto.CategoryDto;
import com.queerxdisasster.virtualbookshelf.entity.Book;
import com.queerxdisasster.virtualbookshelf.entity.BookCategory;
import com.queerxdisasster.virtualbookshelf.entity.Category;
import com.queerxdisasster.virtualbookshelf.repository.BookCategoryRepository;
import com.queerxdisasster.virtualbookshelf.repository.BookRepository;
import com.queerxdisasster.virtualbookshelf.repository.CategoryRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

class BookServiceTest {

    @Mock
    private BookRepository bookRepository;

    @Mock
    private CategoryRepository categoryRepository;

    @Mock
    private BookCategoryRepository bookCategoryRepository;

    @InjectMocks
    private BookService bookService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getAllBooks() {
        Book book1 = new Book();
        book1.setId(1L);
        Book book2 = new Book();
        book2.setId(2L);
        List<Book> books = Arrays.asList(book1, book2);

        when(bookRepository.findAll()).thenReturn(books);

        List<Book> result = bookService.getAllBooks();

        assertEquals(2, result.size());
        verify(bookRepository, times(1)).findAll();
    }

    @Test
    void getBookById() {
        Long bookId = 1L;
        Book book = new Book();
        book.setId(bookId);

        when(bookRepository.findById(bookId)).thenReturn(Optional.of(book));

        Optional<Book> result = bookService.getBookById(bookId);

        assertTrue(result.isPresent());
        assertEquals(bookId, result.get().getId());
        verify(bookRepository, times(1)).findById(bookId);
    }

    @Test
    void getBooksByCategoryId() {
        Long categoryId = 1L;
        Book book1 = new Book();
        book1.setId(1L);
        Book book2 = new Book();
        book2.setId(2L);
        BookCategory bookCategory1 = new BookCategory();
        bookCategory1.setBook(book1);
        BookCategory bookCategory2 = new BookCategory();
        bookCategory2.setBook(book2);
        List<BookCategory> bookCategories = Arrays.asList(bookCategory1, bookCategory2);

        when(bookCategoryRepository.findByCategoryId(categoryId)).thenReturn(bookCategories);

        List<Book> result = bookService.getBooksByCategoryId(categoryId);

        assertEquals(2, result.size());
        assertTrue(result.contains(book1));
        assertTrue(result.contains(book2));
        verify(bookCategoryRepository, times(1)).findByCategoryId(categoryId);
    }

    @Test
    void saveBook() {
        BookDto bookDto = new BookDto();
        bookDto.setTitle("Test Title");
        bookDto.setAuthor("Test Author");
        bookDto.setDescription("Test Description");
        bookDto.setImageUrl("Test ImageUrl");

        CategoryDto categoryDto = new CategoryDto();
        categoryDto.setName("Test Category");
        bookDto.setCategories(Set.of(categoryDto));

        Category category = new Category();
        category.setId(1L);
        category.setName("Test Category");

        when(categoryRepository.findByName("Test Category")).thenReturn(Optional.of(category));
        when(bookRepository.save(any(Book.class))).thenAnswer(invocation -> {
            Book book = invocation.getArgument(0);
            book.setId(1L);
            return book;
        });

        Book result = bookService.saveBook(bookDto);

        assertEquals("Test Title", result.getTitle());
        assertEquals("Test Author", result.getAuthor());
        assertEquals("Test Description", result.getDescription());
        assertEquals("Test ImageUrl", result.getImageUrl());
        assertEquals(1L, result.getId());
        verify(bookRepository, times(1)).save(any(Book.class));
        verify(bookCategoryRepository, times(1)).save(any(BookCategory.class));
    }
}
