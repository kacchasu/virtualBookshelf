package com.queerxdisasster.virtualbookshelf.service;

import com.queerxdisasster.virtualbookshelf.entity.SharedBookshelfBook;
import com.queerxdisasster.virtualbookshelf.repository.SharedBookshelfBookRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class SharedBookshelfBookServiceTest {

    @Mock
    private SharedBookshelfBookRepository sharedBookshelfBookRepository;

    @InjectMocks
    private SharedBookshelfBookService sharedBookshelfBookService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void findBySharedBookshelfId() {
        Long sharedBookshelfId = 1L;
        SharedBookshelfBook book1 = new SharedBookshelfBook();
        book1.setId(1L);
        SharedBookshelfBook book2 = new SharedBookshelfBook();
        book2.setId(2L);
        List<SharedBookshelfBook> books = Arrays.asList(book1, book2);

        when(sharedBookshelfBookRepository.findBySharedBookshelfId(sharedBookshelfId)).thenReturn(books);

        List<SharedBookshelfBook> result = sharedBookshelfBookService.findBySharedBookshelfId(sharedBookshelfId);

        assertEquals(2, result.size());
        assertEquals(book1.getId(), result.get(0).getId());
        assertEquals(book2.getId(), result.get(1).getId());
        verify(sharedBookshelfBookRepository, times(1)).findBySharedBookshelfId(sharedBookshelfId);
    }

    @Test
    void findByBookId() {
        Long bookId = 1L;
        SharedBookshelfBook book1 = new SharedBookshelfBook();
        book1.setId(1L);
        SharedBookshelfBook book2 = new SharedBookshelfBook();
        book2.setId(2L);
        List<SharedBookshelfBook> books = Arrays.asList(book1, book2);

        when(sharedBookshelfBookRepository.findByBookId(bookId)).thenReturn(books);

        List<SharedBookshelfBook> result = sharedBookshelfBookService.findByBookId(bookId);

        assertEquals(2, result.size());
        assertEquals(book1.getId(), result.get(0).getId());
        assertEquals(book2.getId(), result.get(1).getId());
        verify(sharedBookshelfBookRepository, times(1)).findByBookId(bookId);
    }

    @Test
    void save() {
        SharedBookshelfBook book = new SharedBookshelfBook();
        book.setId(1L);

        when(sharedBookshelfBookRepository.save(book)).thenReturn(book);

        SharedBookshelfBook result = sharedBookshelfBookService.save(book);

        assertEquals(book.getId(), result.getId());
        verify(sharedBookshelfBookRepository, times(1)).save(book);
    }
}
