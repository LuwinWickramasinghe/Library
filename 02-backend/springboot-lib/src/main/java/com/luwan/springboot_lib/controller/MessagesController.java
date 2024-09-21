package com.luwan.springboot_lib.controller;


import com.luwan.springboot_lib.entity.Message;
import com.luwan.springboot_lib.requestmodels.AdminQuestionRequest;
import com.luwan.springboot_lib.service.MessagesService;
import com.luwan.springboot_lib.utils.ExtractJWT;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("https://localhost:3000")
@RestController
@RequestMapping("/api/messages")
public class MessagesController {

    private MessagesService messagesService;

    @Autowired
    public MessagesController(MessagesService messagesService) {
        this.messagesService = messagesService;
    }

    @PostMapping("/secure/add/message")
    public void postMessage(@RequestHeader(value = "Authorization") String token,
            @RequestBody Message messageRequest) {
        String userEmail = ExtractJWT.payloadJWTExtraction(token, "\"sub\"");
        messagesService.postMessage(messageRequest, userEmail);
    }

    @PutMapping("/secure/admin/message")
    public  void putMessage(@RequestHeader(value = "Authorization") String token,
                            @RequestBody AdminQuestionRequest adminQuestionRequest) throws Exception {
        String userEmail = ExtractJWT.payloadJWTExtraction(token, "\"sub\"");
        String admin = ExtractJWT.payloadJWTExtraction(token, "\"userType\"");

        if (admin == null || !admin.equals("admin")) {
            throw new Exception("Access denied. Admins Only");
        }
        messagesService.putMessage(adminQuestionRequest, userEmail);
    }
}
