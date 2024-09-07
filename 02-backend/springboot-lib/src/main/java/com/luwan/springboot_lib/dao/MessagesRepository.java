package com.luwan.springboot_lib.dao;

import com.luwan.springboot_lib.entity.Messages;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MessagesRepository extends JpaRepository<Messages, Long> {
}
