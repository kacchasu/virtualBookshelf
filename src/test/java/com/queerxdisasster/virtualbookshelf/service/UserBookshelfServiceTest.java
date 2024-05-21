package com.queerxdisasster.virtualbookshelf.service;

import com.queerxdisasster.virtualbookshelf.entity.UserBookshelf;
import com.queerxdisasster.virtualbookshelf.repository.UserBookshelfRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class UserBookshelfServiceTest {

    @Mock
    private UserBookshelfRepository userBookshelfRepository;

    @InjectMocks
    private UserBookshelfService userBookshelfService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void findByUserId() {
        Long userId = 1L;
        UserBookshelf bookshelf1 = new UserBookshelf();
        bookshelf1.setId(1L);
        UserBookshelf bookshelf2 = new UserBookshelf();
        bookshelf2.setId(2L);
        List<UserBookshelf> bookshelves = Arrays.asList(bookshelf1, bookshelf2);

        when(userBookshelfRepository.findByUserId(userId)).thenReturn(bookshelves);

        List<UserBookshelf> result = userBookshelfService.findByUserId(userId);

        assertEquals(2, result.size());
        assertEquals(bookshelf1.getId(), result.get(0).getId());
        assertEquals(bookshelf2.getId(), result.get(1).getId());
        verify(userBookshelfRepository, times(1)).findByUserId(userId);
    }

    @Test
    void findByBookshelfId() {
        Long bookshelfId = 1L;
        UserBookshelf bookshelf1 = new UserBookshelf();
        bookshelf1.setId(1L);
        UserBookshelf bookshelf2 = new UserBookshelf();
        bookshelf2.setId(2L);
        List<UserBookshelf> bookshelves = Arrays.asList(bookshelf1, bookshelf2);

        when(userBookshelfRepository.findBySharedBookshelfId(bookshelfId)).thenReturn(bookshelves);

        List<UserBookshelf> result = userBookshelfService.findByBookshelfId(bookshelfId);

        assertEquals(2, result.size());
        assertEquals(bookshelf1.getId(), result.get(0).getId());
        assertEquals(bookshelf2.getId(), result.get(1).getId());
        verify(userBookshelfRepository, times(1)).findBySharedBookshelfId(bookshelfId);
    }
}
