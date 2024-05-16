package com.queerxdisasster.virtualbookshelf.repository;

import com.queerxdisasster.virtualbookshelf.entity.UserBookshelf;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Repository
public interface UserBookshelfRepository extends JpaRepository<UserBookshelf, Long> {
    List<UserBookshelf> findByUserId(Long userId);

    Optional<UserBookshelf> findBySharedBookshelfIdAndUserId(Long bookshelfId, Long userId);

    void deleteBySharedBookshelfIdAndUserId(Long bookshelfId, Long userId);
}