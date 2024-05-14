package com.queerxdisasster.virtualbookshelf.repository;

import com.queerxdisasster.virtualbookshelf.entity.SharedBookshelf;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SharedBookshelfRepository extends JpaRepository<SharedBookshelf, Long> {
}