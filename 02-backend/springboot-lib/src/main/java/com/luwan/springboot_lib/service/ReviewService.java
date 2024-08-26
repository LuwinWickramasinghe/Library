package com.luwan.springboot_lib.service;

import com.luwan.springboot_lib.dao.BookRepositary;
import com.luwan.springboot_lib.dao.ReviewRepository;
import com.luwan.springboot_lib.entity.Review;
import com.luwan.springboot_lib.requestmodels.ReviewRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.time.LocalDate;

@Service
@Transactional
public class ReviewService {

    private BookRepositary bookRepositary;

    private ReviewRepository reviewRepository;

    @Autowired
    public ReviewService(BookRepositary bookRepositary, ReviewRepository reviewRepository) {
        this.bookRepositary = bookRepositary;
        this.reviewRepository = reviewRepository;
    }

    public void postReview(String userEmail, ReviewRequest reviewRequest) throws Exception {
        Review validateReview = reviewRepository.findByUserEmailAndBookId(userEmail, reviewRequest.getBookId());
        if (validateReview != null) {
            throw new Exception("A Review already published!");
        }

        Review review = new Review();
        review.setBookId(reviewRequest.getBookId());
        review.setRating(reviewRequest.getRating());
        review.setUserEmail(userEmail);

        if (reviewRequest.getReviewDescription().isPresent()) {
            review.setReviewDescription(reviewRequest.getReviewDescription().map(
                    Object::toString
            ).orElse(null));
        }

        review.setDate(Date.valueOf(LocalDate.now()));
        reviewRepository.save(review);



    }
}
