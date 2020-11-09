package com.bigcart.productservice.bigcartproductservice.Services;

import com.bigcart.productservice.bigcartproductservice.Model.Review;
import com.bigcart.productservice.bigcartproductservice.Model.VendorProduct;

import java.util.List;

public interface ReviewService {
    Review findById(Long id);
    Review findById(String id);
    List<Review> findAll();
    List<Review> findAllByVendorProduct(VendorProduct vendorProduct);
    Review save(Review review);
    Review update(Review review);
    void delete(Review review);


}
