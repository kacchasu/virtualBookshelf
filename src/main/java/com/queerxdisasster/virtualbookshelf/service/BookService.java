package com.queerxdisasster.virtualbookshelf.service;

import com.queerxdisasster.virtualbookshelf.entity.Book;
import com.queerxdisasster.virtualbookshelf.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    @Transactional
    public Book save(Book book) {
        return bookRepository.save(book);
    }

    public Book findById(Long id) {
        return bookRepository.findById(id).orElseThrow(() -> new RuntimeException("Book not found"));
    }

    public List<Book> findAllBooks() {
        return bookRepository.findAll();
    }
}