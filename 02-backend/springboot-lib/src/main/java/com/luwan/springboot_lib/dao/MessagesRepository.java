package com.luwan.springboot_lib.dao;

import com.luwan.springboot_lib.entity.Messages;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.RequestParam;

public interface MessagesRepository extends JpaRepository<Messages, Long> {

    Page<Messages> findByUserEmail(@RequestParam("user_email") String userEmail, Pageable pageable);
}
