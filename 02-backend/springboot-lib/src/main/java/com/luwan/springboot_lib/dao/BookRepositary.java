package com.luwan.springboot_lib.dao;

import com.luwan.springboot_lib.entity.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.RequestParam;

public interface BookRepositary extends JpaRepository<Book, Long> {
    Page<Book> findByTitleContaining (@RequestParam("title") String title, Pageable pageable);

}

