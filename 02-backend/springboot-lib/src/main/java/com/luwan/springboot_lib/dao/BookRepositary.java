package com.luwan.springboot_lib.dao;

import com.luwan.springboot_lib.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepositary extends JpaRepository<Book, Long> {

}

