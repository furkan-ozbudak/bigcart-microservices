package com.bigcart.productservice.bigcartproductservice;

import com.bigcart.productservice.bigcartproductservice.Controller.ProductController;
import com.bigcart.productservice.bigcartproductservice.Model.VendorProduct;
import com.bigcart.productservice.bigcartproductservice.Services.ProductImageService;
import com.bigcart.productservice.bigcartproductservice.Services.ProductService;
import com.bigcart.productservice.bigcartproductservice.Services.VendorProductService;
import com.bigcart.productservice.bigcartproductservice.Services.VendorProductServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.event.EventListener;

import java.io.File;

@SpringBootApplication
@EnableDiscoveryClient
public class BigcartProductServiceApplication {
    @Autowired
    VendorProductService vendorProductService;

    @Autowired
    ProductImageService productImageService;
    public static void main(String[] args) {
        new File(ProductController.uploadDirectory).mkdir();
        SpringApplication.run(BigcartProductServiceApplication.class, args);
    }

    @EventListener(ApplicationReadyEvent.class)
    public void doSomethingAfterStartup() {

        vendorProductService.synchronizeRatings();
        productImageService.loadSampleImages();

    }

}
