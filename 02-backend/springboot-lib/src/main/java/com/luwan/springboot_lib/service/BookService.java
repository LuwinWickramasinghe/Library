package com.luwan.springboot_lib.service;


import com.luwan.springboot_lib.dao.BookRepositary;
import com.luwan.springboot_lib.dao.CheckoutRepository;
import com.luwan.springboot_lib.entity.Book;
import com.luwan.springboot_lib.entity.Checkout;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.Optional;

@Service
@Transactional

public class BookService {

    private BookRepositary bookRepositary;

    private CheckoutRepository checkoutRepository;

    public BookService(BookRepositary bookRepositary, CheckoutRepository checkoutRepository) {
        this.bookRepositary = bookRepositary;
        this.checkoutRepository = checkoutRepository;
    }

    public Book checkoutBook(String userEmail, Long bookId) throws Exception {
        Optional<Book> book = bookRepositary.findById(bookId);

        Checkout validateCheckout = checkoutRepository.findByUserEmailAndBookId(userEmail, bookId);

        if (book.isEmpty() || validateCheckout != null || book.get().getCopiesAvailable() <=0) {
            throw new Exception("Book does not exist or has no copies available or already checked out by the user");
        }

        book.get().setCopiesAvailable(book.get().getCopiesAvailable() - 1);
        bookRepositary.save(book.get());

        Checkout checkout = new Checkout(
                userEmail,
                LocalDate.now().toString(),
                LocalDate.now().plusDays(7).toString(),
                book.get().getId()
        );

        checkoutRepository.save(checkout);

        return book.get();

    }

    public Boolean checkoutBookByUser(String userEmail, Long bookId) throws Exception {
        Checkout validateCheckout = checkoutRepository.findByUserEmailAndBookId(userEmail, bookId);
        if (validateCheckout == null) {
            return false;

        }else {
            return true;
        }
    }

    public int currentLoansCount(String userEmail) throws Exception {
        return checkoutRepository.findBooksByUserEmail(userEmail).size();
    }

}
