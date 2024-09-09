package com.luwan.springboot_lib.dao;

import com.luwan.springboot_lib.entity.Message;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

public interface MessagesRepository extends JpaRepository<Message, Long> {

    Page<Message> findByUserEmail(@Param("userEmail") String userEmail, Pageable pageable);
}
