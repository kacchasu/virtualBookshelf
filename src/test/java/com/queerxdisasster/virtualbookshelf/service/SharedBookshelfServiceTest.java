package com.queerxdisasster.virtualbookshelf.service;

import com.queerxdisasster.virtualbookshelf.entity.SharedBookshelf;
import com.queerxdisasster.virtualbookshelf.entity.User;
import com.queerxdisasster.virtualbookshelf.entity.UserBookshelf;
import com.queerxdisasster.virtualbookshelf.repository.SharedBookshelfRepository;
import com.queerxdisasster.virtualbookshelf.repository.UserBookshelfRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

class SharedBookshelfServiceTest {

    @Mock
    private SharedBookshelfRepository sharedBookshelfRepository;

    @Mock
    private UserBookshelfRepository userBookshelfRepository;

    @InjectMocks
    private SharedBookshelfService sharedBookshelfService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getAllSharedBookshelves() {
        SharedBookshelf bookshelf1 = new SharedBookshelf();
        bookshelf1.setId(1L);
        SharedBookshelf bookshelf2 = new SharedBookshelf();
        bookshelf2.setId(2L);
        List<SharedBookshelf> bookshelves = Arrays.asList(bookshelf1, bookshelf2);

        when(sharedBookshelfRepository.findAll()).thenReturn(bookshelves);

        List<SharedBookshelf> result = sharedBookshelfService.getAllSharedBookshelves();

        assertEquals(2, result.size());
        verify(sharedBookshelfRepository, times(1)).findAll();
    }

    @Test
    void getSharedBookshelvesByUserId() {
        Long userId = 1L;
        SharedBookshelf bookshelf1 = new SharedBookshelf();
        bookshelf1.setId(1L);
        SharedBookshelf bookshelf2 = new SharedBookshelf();
        bookshelf2.setId(2L);
        UserBookshelf userBookshelf1 = new UserBookshelf();
        userBookshelf1.setSharedBookshelf(bookshelf1);
        UserBookshelf userBookshelf2 = new UserBookshelf();
        userBookshelf2.setSharedBookshelf(bookshelf2);
        List<UserBookshelf> userBookshelves = Arrays.asList(userBookshelf1, userBookshelf2);

        when(userBookshelfRepository.findByUserId(userId)).thenReturn(userBookshelves);

        List<SharedBookshelf> result = sharedBookshelfService.getSharedBookshelvesByUserId(userId);

        assertEquals(2, result.size());
        assertTrue(result.contains(bookshelf1));
        assertTrue(result.contains(bookshelf2));
        verify(userBookshelfRepository, times(1)).findByUserId(userId);
    }

    @Test
    void getSharedBookshelfById() {
        Long bookshelfId = 1L;
        SharedBookshelf bookshelf = new SharedBookshelf();
        bookshelf.setId(bookshelfId);

        when(sharedBookshelfRepository.findById(bookshelfId)).thenReturn(Optional.of(bookshelf));

        Optional<SharedBookshelf> result = sharedBookshelfService.getSharedBookshelfById(bookshelfId);

        assertTrue(result.isPresent());
        assertEquals(bookshelfId, result.get().getId());
        verify(sharedBookshelfRepository, times(1)).findById(bookshelfId);
    }

    @Test
    void saveSharedBookshelf() {
        SharedBookshelf bookshelf = new SharedBookshelf();
        bookshelf.setId(1L);
        User owner = new User();
        owner.setId(1L);

        when(sharedBookshelfRepository.save(bookshelf)).thenReturn(bookshelf);

        SharedBookshelf result = sharedBookshelfService.saveSharedBookshelf(bookshelf, owner);

        assertEquals(bookshelf.getId(), result.getId());
        verify(sharedBookshelfRepository, times(1)).save(bookshelf);
        verify(userBookshelfRepository, times(1)).save(any(UserBookshelf.class));
    }

    @Test
    void isOwner() {
        Long bookshelfId = 1L;
        Long userId = 1L;
        UserBookshelf userBookshelf = new UserBookshelf();
        userBookshelf.setOwner(true);

        when(userBookshelfRepository.findBySharedBookshelfIdAndUserId(bookshelfId, userId)).thenReturn(Optional.of(userBookshelf));

        boolean result = sharedBookshelfService.isOwner(bookshelfId, userId);

        assertTrue(result);
        verify(userBookshelfRepository, times(1)).findBySharedBookshelfIdAndUserId(bookshelfId, userId);
    }

    @Test
    void removeUserFromBookshelf() {
        Long bookshelfId = 1L;
        Long userId = 1L;

        doNothing().when(userBookshelfRepository).deleteBySharedBookshelfIdAndUserId(bookshelfId, userId);

        sharedBookshelfService.removeUserFromBookshelf(bookshelfId, userId);

        verify(userBookshelfRepository, times(1)).deleteBySharedBookshelfIdAndUserId(bookshelfId, userId);
    }
}
