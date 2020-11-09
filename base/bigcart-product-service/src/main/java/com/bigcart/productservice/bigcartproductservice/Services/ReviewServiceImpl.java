package com.bigcart.productservice.bigcartproductservice.Services;

import com.bigcart.productservice.bigcartproductservice.Model.Review;
import com.bigcart.productservice.bigcartproductservice.Model.VendorProduct;
import com.bigcart.productservice.bigcartproductservice.Repository.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReviewServiceImpl implements ReviewService{
    @Autowired
    ReviewRepository reviewRepository;

    @Override
    public Review findById(Long id) {
        return reviewRepository.findById(id).orElse(null);
    }
    @Override
    public Review findById(String id) {
        Long rid;
        try {
            rid = Long.parseLong(id);
        }
        catch (Exception e) {
            return null;
        }
        return reviewRepository.findById(rid).orElse(null);
    }

    @Override
    public List<Review> findAll() {
        return reviewRepository.findAll();
    }

    @Override
    public List<Review> findAllByVendorProduct(VendorProduct vendorProduct) {
        if(vendorProduct == null) {
            return null;
        }
        return reviewRepository.findAllByVendorProduct(vendorProduct);
    }

    @Override
    public Review save(Review review) {
        return reviewRepository.save(review);
    }

    @Override
    public Review update(Review review) {
        Review r = reviewRepository.findById(review.getReview_id()).get();
        if(r == null) {
            return null;
        }
        return reviewRepository.save(review);
    }

    @Override
    public void delete(Review review) {
         reviewRepository.deleteById(review.getReview_id());
    }
}
