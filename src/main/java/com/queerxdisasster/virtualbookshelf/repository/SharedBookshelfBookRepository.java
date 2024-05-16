package com.queerxdisasster.virtualbookshelf.repository;

import com.queerxdisasster.virtualbookshelf.entity.SharedBookshelfBook;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SharedBookshelfBookRepository extends JpaRepository<SharedBookshelfBook, Long> {
    List<SharedBookshelfBook> findBySharedBookshelfId(Long sharedBookshelfId);
    List<SharedBookshelfBook> findByBookId(Long bookId);
}
