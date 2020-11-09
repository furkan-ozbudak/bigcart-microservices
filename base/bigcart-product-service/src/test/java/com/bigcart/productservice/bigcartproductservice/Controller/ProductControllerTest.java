package com.bigcart.productservice.bigcartproductservice.Controller;

import com.bigcart.productservice.bigcartproductservice.Model.Category;
import com.bigcart.productservice.bigcartproductservice.Model.Product;
import com.bigcart.productservice.bigcartproductservice.Services.ProductService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;

@SpringBootTest
class ProductControllerTest {

    @Mock
    ProductService productService;

    @InjectMocks
    ProductController productController;

    @Test
    void getProduct() {

        Product product = new Product();

        when(productService.findById (1l)).thenReturn(product);
        productController.getProduct(1l);
        Mockito.verify(productService, times(1)).findById(1l);

    }

    @Test
    void getAllProducts(){


        Product product = new Product();
        List<Product> products= new ArrayList<>();
        products.add(product);
        when(productService.findAll ()).thenReturn(products);
        productController.getProducts();
        Mockito.verify(productService, times(1)).findAll();

    }

    @Test
    void productNames() {
    }
}