package com.bigcart.productservice.bigcartproductservice.Repository;

import com.bigcart.productservice.bigcartproductservice.Model.ProductImage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductImageRepository extends JpaRepository<ProductImage, Long> {
}
