package com.luwan.springboot_lib.service;


import com.luwan.springboot_lib.dao.BookRepositary;
import com.luwan.springboot_lib.dao.CheckoutRepository;
import com.luwan.springboot_lib.entity.Book;
import com.luwan.springboot_lib.entity.Checkout;
import com.luwan.springboot_lib.responsemodels.ShelfCurrentLoanResponse;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

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

    public List<ShelfCurrentLoanResponse> currentLoans(String userEmail) throws Exception {

        List<ShelfCurrentLoanResponse> shelfCurrentLoanResponses = new ArrayList<>();

        List<Checkout> checkoutList = checkoutRepository.findBooksByUserEmail(userEmail);

        List<Long> bookIdList = new ArrayList<>();

        for (Checkout i : checkoutList) {
            bookIdList.add(i.getId());
        }

        List<Book> books = bookRepositary.findAllByIdIn(bookIdList);

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        for (Book book : books) {
            Optional<Checkout> checkout = checkoutList.stream().filter(
                    x -> x.getBookId() == book.getId()).findFirst();

            if(checkout.isPresent()) {

                Date d1 = sdf.parse(checkout.get().getReturnDate());
                Date d2 = sdf.parse(LocalDate.now().toString());

                TimeUnit time = TimeUnit.DAYS;

                long difference_In_Time = time.convert(d1.getTime() - d2.getTime(), TimeUnit.MILLISECONDS);

                shelfCurrentLoanResponses.add(new ShelfCurrentLoanResponse(book,(int) difference_In_Time));
            }
        }
        return shelfCurrentLoanResponses;

    }

    public void returnBook(String userEmail, Long bookId) throws Exception {

        Optional<Book> book = bookRepositary.findById(bookId);

        Checkout validateCheckout = checkoutRepository.findByUserEmailAndBookId(userEmail, bookId);

        if (validateCheckout == null || book.isEmpty()) {
            throw new Exception("There is no loaned book with this id or book does not exist");

        }

        book.get().setCopiesAvailable(book.get().getCopiesAvailable() + 1);

        bookRepositary.save(book.get());

        checkoutRepository.deleteById(validateCheckout.getId());
    }

}
