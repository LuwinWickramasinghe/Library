package com.luwan.springboot_lib.requestmodels;


import lombok.Data;

@Data
public class AddBookRequest {

    private String title;

    private String author;

    private String description;

    private int copies;

    private String categories;

    private String img;

}
