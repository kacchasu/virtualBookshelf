package com.queerxdisasster.virtualbookshelf.service;

import com.queerxdisasster.virtualbookshelf.entity.Review;
import com.queerxdisasster.virtualbookshelf.repository.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ReviewService {

    @Autowired
    private ReviewRepository reviewRepository;

    @Transactional
    public Review save(Review review) {
        return reviewRepository.save(review);
    }

    public List<Review> findByBookId(Long bookId) {
        return reviewRepository.findAllByBookId(bookId);
    }
}