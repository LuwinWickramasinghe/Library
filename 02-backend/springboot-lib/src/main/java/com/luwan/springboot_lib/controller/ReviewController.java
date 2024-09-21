package com.luwan.springboot_lib.controller;

import com.luwan.springboot_lib.requestmodels.ReviewRequest;
import com.luwan.springboot_lib.service.ReviewService;
import com.luwan.springboot_lib.utils.ExtractJWT;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("https://localhost:3000")
@RestController
@RequestMapping("/api/reviews")
public class ReviewController {

    private final ReviewService reviewService;

    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;

    }

    @GetMapping("/secure/user/book")
    public Boolean reviewBookByUser(@RequestHeader(value = "Authorization") String token,
                                    @RequestParam Long bookId) throws Exception {
        String userEmail = ExtractJWT.payloadJWTExtraction(token, "\"sub\"");

        if (userEmail == null) {
            throw new Exception("user email is null");
        }
        return reviewService.userReviewListed(userEmail, bookId);
    }

    @PostMapping("/secure")
    public void postReview(@RequestHeader(value="Authorization") String token,
                           @RequestBody ReviewRequest reviewRequest) throws Exception {
        String userEmail = ExtractJWT.payloadJWTExtraction(token,"\"sub\"");
        if(userEmail == null) {
            throw new Exception("Your email is missing");
        }
        reviewService.postReview(userEmail, reviewRequest);
    }
}
