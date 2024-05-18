package com.queerxdisasster.virtualbookshelf.service;

import com.queerxdisasster.virtualbookshelf.entity.UserBookshelf;
import com.queerxdisasster.virtualbookshelf.repository.UserBookshelfRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserBookshelfService {
    private final UserBookshelfRepository userBookshelfRepository;

    public List<UserBookshelf> findByUserId(Long userId) {
        return userBookshelfRepository.findByUserId(userId);
    }
    public List<UserBookshelf> findByBookshelfId(Long userId) {
        return userBookshelfRepository.findBySharedBookshelfId(userId);
    }
}
