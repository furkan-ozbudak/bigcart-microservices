package com.bigcart.productservice.bigcartproductservice.Repository;

import com.bigcart.productservice.bigcartproductservice.Model.Review;
import com.bigcart.productservice.bigcartproductservice.Model.VendorProduct;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review,Long> {
    List<Review> findAllByVendorProduct(VendorProduct vendorProduct);
}
