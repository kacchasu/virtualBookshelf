package com.queerxdisasster.virtualbookshelf.service;

import com.queerxdisasster.virtualbookshelf.entity.SharedBookshelf;
import com.queerxdisasster.virtualbookshelf.entity.User;
import com.queerxdisasster.virtualbookshelf.entity.UserBookshelf;
import com.queerxdisasster.virtualbookshelf.repository.SharedBookshelfRepository;
import com.queerxdisasster.virtualbookshelf.repository.UserBookshelfRepository;
import com.queerxdisasster.virtualbookshelf.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SharedBookshelfService {
    private final SharedBookshelfRepository sharedBookshelfRepository;
    private final UserBookshelfRepository userBookshelfRepository;

    public List<SharedBookshelf> getAllSharedBookshelves() {
        return sharedBookshelfRepository.findAll();
    }

    public List<SharedBookshelf> getSharedBookshelvesByUserId(Long userId) {
        return userBookshelfRepository.findByUserId(userId)
                .stream()
                .map(UserBookshelf::getSharedBookshelf)
                .collect(Collectors.toList());
    }

    public Optional<SharedBookshelf> getSharedBookshelfById(Long id) {
        return sharedBookshelfRepository.findById(id);
    }

    public SharedBookshelf saveSharedBookshelf(SharedBookshelf sharedBookshelf, User owner) {
        sharedBookshelf = sharedBookshelfRepository.save(sharedBookshelf);
        UserBookshelf userBookshelf = new UserBookshelf();
        userBookshelf.setSharedBookshelf(sharedBookshelf);
        userBookshelf.setUser(owner);
        userBookshelf.setOwner(true);
        userBookshelfRepository.save(userBookshelf);
        return sharedBookshelf;
    }

    public boolean isOwner(Long bookshelfId, Long userId) {
        return userBookshelfRepository.findBySharedBookshelfIdAndUserId(bookshelfId, userId)
                .map(UserBookshelf::isOwner)
                .orElse(false);
    }

    @Transactional
    public void removeUserFromBookshelf(Long bookshelfId, Long userId) {
        userBookshelfRepository.deleteBySharedBookshelfIdAndUserId(bookshelfId, userId);
    }
}