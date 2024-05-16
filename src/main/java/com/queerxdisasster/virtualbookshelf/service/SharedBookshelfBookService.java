package com.queerxdisasster.virtualbookshelf.service;

import com.queerxdisasster.virtualbookshelf.entity.SharedBookshelfBook;
import com.queerxdisasster.virtualbookshelf.repository.SharedBookshelfBookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SharedBookshelfBookService {
    private final SharedBookshelfBookRepository sharedBookshelfBookRepository;

    public List<SharedBookshelfBook> findBySharedBookshelfId(Long sharedBookshelfId) {
        return sharedBookshelfBookRepository.findBySharedBookshelfId(sharedBookshelfId);
    }

    public List<SharedBookshelfBook> findByBookId(Long bookId) {
        return sharedBookshelfBookRepository.findByBookId(bookId);
    }

    public SharedBookshelfBook save(SharedBookshelfBook sharedBookshelfBook) {
        return sharedBookshelfBookRepository.save(sharedBookshelfBook);
    }
}
