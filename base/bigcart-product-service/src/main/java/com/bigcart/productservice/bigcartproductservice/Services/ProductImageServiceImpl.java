package com.bigcart.productservice.bigcartproductservice.Services;

import com.bigcart.productservice.bigcartproductservice.Controller.ProductController;
import com.bigcart.productservice.bigcartproductservice.Model.ProductImage;
import com.bigcart.productservice.bigcartproductservice.Repository.ProductImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.imageio.ImageIO;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.io.InputStream;

@Service
public class ProductImageServiceImpl implements ProductImageService {
    @Autowired
    ProductImageRepository productImageRepository;

    @Override
    public List<ProductImage> findAll() {
        return productImageRepository.findAll();
    }

    @Override
    public ProductImage findById(Long id) {
        return productImageRepository.findById(id).get();
    }

    @Override
    public ProductImage save(ProductImage productImage) {
        return productImageRepository.save(productImage);
    }

    @Override
    public void deleteById(Long id) {
        productImageRepository.deleteById(id);
    }

    @Override
    public void loadSampleImages() {
        Path path = Paths.get(ProductController.uploadDirectory);
        try {
            if(!Files.exists(path))
            Files.createDirectory (path);
        } catch (IOException e) {
            e.printStackTrace();
        }

        for (int i =1;i<11;i++) {
            String fullPath = new StringBuilder(ProductController.uploadDirectory).append("/").
                    append(i).append(".").append("jpg").toString();
            try {

                InputStream in = getClass().getResourceAsStream("/uploads/" + i+".jpg");
                ImageIO.write(ImageIO.read(in), "jpg", new File(fullPath));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }
}
