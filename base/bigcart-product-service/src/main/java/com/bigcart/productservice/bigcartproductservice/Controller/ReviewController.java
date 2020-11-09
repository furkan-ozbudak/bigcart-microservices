package com.bigcart.productservice.bigcartproductservice.Controller;

import com.bigcart.productservice.bigcartproductservice.Model.Review;
import com.bigcart.productservice.bigcartproductservice.Model.VendorProduct;
import com.bigcart.productservice.bigcartproductservice.Services.ReviewService;
import com.bigcart.productservice.bigcartproductservice.Services.VendorProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("review")
public class ReviewController {
    @Autowired
    VendorProductService vendorProductService;

    @Autowired
    ReviewService reviewService;

    @GetMapping(value = "/")
    public ResponseEntity findAllReviews() {
        List<Review> reviewList = reviewService.findAll();
        return new ResponseEntity(reviewList, new HttpHeaders(), HttpStatus.OK);
    }

    @GetMapping(value = "/getById/{id}")
    public ResponseEntity getById(@PathVariable String id) {
        Review review = reviewService.findById(id);

        if (review == null) {
            return new ResponseEntity("No such review found.", new HttpHeaders(), HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity(review, new HttpHeaders(), HttpStatus.OK);
    }

    @PostMapping(value = "/")
    public ResponseEntity addReview(@RequestBody Map<String, String> request) {

        if (request.get("userId") == null || request.get("comment") == null ||
                request.get("rating") == null || request.get("vendorProductId") == null) {
            return new ResponseEntity("All fields of review is required.", new HttpHeaders(), HttpStatus.BAD_REQUEST);
        }

        Long userId;
        int newRating;
        Long vendorId;
        Long productId;
        VendorProduct vendorProduct;
        try {
            String[] s = request.get("vendorProductId").split("-");
            vendorId = Long.parseLong(s[0]);
            productId = Long.parseLong(s[1]);
            vendorProduct = vendorProductService.findById(vendorId, productId);
            userId = Long.parseLong(request.get("userId"));
            newRating = Integer.parseInt(request.get("rating"));
        } catch (Exception e) {
            return new ResponseEntity("Bad user id, vendor product id or rating inputted.", new HttpHeaders(), HttpStatus.BAD_REQUEST);
        }
        if (vendorProduct == null) {
            return new ResponseEntity("No such vendor product found.", new HttpHeaders(), HttpStatus.BAD_REQUEST);
        }

        if (newRating < 1 || newRating > 5) {
            return new ResponseEntity("Rating should be a whole number between 1 to 5.", new HttpHeaders(), HttpStatus.BAD_REQUEST);
        }

        List<Review> reviewList = reviewService.findAllByVendorProduct(vendorProduct);
        for (Review review : reviewList) {
            if (review.getUserId().equals(userId)) {
                return new ResponseEntity("One user can not post a second review on same vendor product.", new HttpHeaders(), HttpStatus.BAD_REQUEST);
            }
        }

        Review review = new Review();
        review.setRating(newRating);
        review.setUserId(userId);
        review.setComment(request.get("comment"));
        review.setVendorProduct(vendorProduct);
        Review r = reviewService.save(review);

        // update rating stats of vendor product
        int ratingCount = vendorProduct.getRatingCount();
        double rating = vendorProduct.getRating();
        if (ratingCount == 0) {
            vendorProduct.setRating(newRating);
            vendorProduct.setRatingCount(1);
            vendorProductService.save(vendorProduct);
            return new ResponseEntity("Congratulations! First review of this product is added.", new HttpHeaders(), HttpStatus.OK);
        }
        else {
            double ratingSum = ratingCount * rating;
            ratingSum += newRating;
            vendorProduct.setRatingCount(++ratingCount);
            vendorProduct.setRating(ratingSum / ratingCount);
            vendorProductService.save(vendorProduct);
            return new ResponseEntity("Review is added.", new HttpHeaders(), HttpStatus.OK);
        }
    }


    // Produces error sometimes. Will be fixed in next git push.
    @DeleteMapping(value = "/{id}")
    public ResponseEntity deleteReview(@PathVariable String id) {
        Review review = reviewService.findById(id);
        if (review == null) {
            return new ResponseEntity("No such review.", new HttpHeaders(), HttpStatus.NOT_FOUND);
        }

        VendorProduct vendorProduct = review.getVendorProduct();
        if(vendorProduct == null) {
            return new ResponseEntity("No such vendor product.", new HttpHeaders(), HttpStatus.NOT_FOUND);
        }

        reviewService.delete(review);
        vendorProductService.synchronizeRatings();

        return new ResponseEntity("Review is deleted.", new HttpHeaders(), HttpStatus.OK);
    }
}
