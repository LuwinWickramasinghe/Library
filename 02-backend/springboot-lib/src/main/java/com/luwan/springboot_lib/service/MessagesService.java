package com.luwan.springboot_lib.service;


import com.luwan.springboot_lib.dao.MessagesRepository;
import com.luwan.springboot_lib.entity.Messages;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class MessagesService {

    private MessagesRepository messagesRepository;

    @Autowired
    public MessagesService(MessagesRepository messagesRepository) {
        this.messagesRepository = messagesRepository;
    }

    public void postMessage(Messages messageRequest, String userEmail) {
        Messages message = new Messages(messageRequest.getTitle(), messageRequest.getQuestion());
        message.setUserEmail(userEmail);
        messagesRepository.save(message);
    }
}
