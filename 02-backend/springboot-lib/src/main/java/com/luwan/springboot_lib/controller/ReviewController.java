package com.luwan.springboot_lib.controller;

import com.luwan.springboot_lib.requestmodels.ReviewRequest;
import com.luwan.springboot_lib.service.ReviewService;
import com.luwan.springboot_lib.utils.ExtractJWT;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("http://localhost:3000")
@RestController
@RequestMapping("/api/reviews")
public class ReviewController {

    private ReviewService reviewService;

    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;

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
