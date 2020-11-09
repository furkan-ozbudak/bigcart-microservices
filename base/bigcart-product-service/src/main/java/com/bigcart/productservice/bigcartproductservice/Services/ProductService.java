package com.bigcart.productservice.bigcartproductservice.Services;

import com.bigcart.productservice.bigcartproductservice.Model.Product;
import com.bigcart.productservice.bigcartproductservice.Model.VendorProduct;
import org.springframework.stereotype.Service;

import java.util.List;

public interface ProductService {
    Product addProduct(Product product);
    Product findById(long productId) ;
    List<Product> findAll();
    Product update(Product product);
    Boolean delete(long productId);
    Product findByCategoryId(Long categoryId);


}
