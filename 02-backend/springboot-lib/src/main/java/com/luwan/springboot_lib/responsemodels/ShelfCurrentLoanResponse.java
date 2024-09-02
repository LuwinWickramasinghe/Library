package com.luwan.springboot_lib.responsemodels;


import com.luwan.springboot_lib.entity.Book;
import lombok.Data;

@Data
public class ShelfCurrentLoanResponse {

    public ShelfCurrentLoanResponse(Book book, int daysLeft) {
        this.book = book;
        this.daysLeft = daysLeft;
    }

    private Book book;

    private int daysLeft;
}
