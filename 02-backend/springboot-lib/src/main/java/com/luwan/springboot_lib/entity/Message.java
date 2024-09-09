package com.luwan.springboot_lib.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name= "messages")
@Data
public class Message {

    public Message(){}

    public Message(String title, String question){
        this.title = title;
        this.question = question;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "title")
    private String title;

    @Column(name = "user_email")
    private String userEmail;

    @Column(name = "question")
    private String question;

    @Column(name = "admin_email")
    private String adminEmail;

    @Column(name = "response")
    private String response;

    @Column(name = "closed")
    private Boolean closed;
}
