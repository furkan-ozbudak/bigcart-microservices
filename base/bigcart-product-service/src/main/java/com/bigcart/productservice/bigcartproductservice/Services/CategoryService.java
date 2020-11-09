package com.bigcart.productservice.bigcartproductservice.Services;

import com.bigcart.productservice.bigcartproductservice.Model.Category;

import java.util.List;

public interface CategoryService {
    public List<Category> findAll();
    public List<Category> findAllByParentCategoryId(Long parentCategoryId);
    public Category findById(long id) ;
    public Category save(Category category);
    public Category update(Category category);
    public Boolean delete(long id);
}
