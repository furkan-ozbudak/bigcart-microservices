package com.bigcart.productservice.bigcartproductservice.Services;

import com.bigcart.productservice.bigcartproductservice.Model.ProductImage;

import java.util.List;

public interface ProductImageService {
    public List<ProductImage> findAll();
    public ProductImage findById(Long id);
    public ProductImage save(ProductImage productImage);
    public void deleteById(Long id);
    public void loadSampleImages ();
}
