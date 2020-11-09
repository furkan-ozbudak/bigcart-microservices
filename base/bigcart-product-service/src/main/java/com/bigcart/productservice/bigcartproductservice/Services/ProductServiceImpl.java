package com.bigcart.productservice.bigcartproductservice.Services;

import com.bigcart.productservice.bigcartproductservice.Model.Product;
import com.bigcart.productservice.bigcartproductservice.Model.VendorProduct;
import com.bigcart.productservice.bigcartproductservice.Repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    ProductRepository productRepository;

    @Override
    public Product addProduct(Product product) {
        return productRepository.save(product);
    }

    @Override
    public Product findById(long productId) {
        return productRepository.findById(productId).orElse(null);
    }

    @Override
    public List<Product> findAll() {

        return productRepository.findAll();
    }

    @Override
    public Product update(Product p) {
        Product product = productRepository.findById(p.getProductId()).get();
        if (product == null) {
            return null;
        }
        return productRepository.save(product);
    }

    @Override
    public Boolean delete(long productId) {
        return null;
    }

    @Override
    public Product findByCategoryId(Long categoryId) {
        return productRepository.findByCategoryId(categoryId);
    }


}
