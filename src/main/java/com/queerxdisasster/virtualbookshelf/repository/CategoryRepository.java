package com.queerxdisasster.virtualbookshelf.repository;

import com.queerxdisasster.virtualbookshelf.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
}