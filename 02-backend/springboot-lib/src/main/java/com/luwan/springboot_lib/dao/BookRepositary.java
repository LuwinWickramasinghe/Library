package com.luwan.springboot_lib.dao;

import com.luwan.springboot_lib.entity.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public interface BookRepositary extends JpaRepository<Book, Long> {
    Page<Book> findByTitleContaining (@RequestParam("title") String title, Pageable pageable);

    Page<Book> findByCategory(@RequestParam("category") String category, Pageable pageable);


    List<Book> findAllByIdIn(@Param("book_Ids") List<Long> bookId);
}

