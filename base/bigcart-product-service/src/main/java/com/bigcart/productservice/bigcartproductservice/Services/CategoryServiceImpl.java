package com.bigcart.productservice.bigcartproductservice.Services;

import com.bigcart.productservice.bigcartproductservice.Model.Category;
import com.bigcart.productservice.bigcartproductservice.Repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    CategoryRepository categoryRepository;

    @Override
    public Category save(Category category) {
        return categoryRepository.save(category) ;
    }

    @Override
    public Category findById(long id) {
        return categoryRepository.findById(id).orElse(null);
    }

    @Override
    public List<Category> findAll() {
        return categoryRepository.findAll();
    }

    @Override
    public List<Category> findAllByParentCategoryId(Long parentCategoryId) {
        return categoryRepository.findAllByParentCategoryId(parentCategoryId);
    }

    @Override
    public Category update(Category category) {
        Category c = categoryRepository.findById(category.getCategoryId()).orElse(null);
        if (c==null)
            return null;
        category.setName(category.getName());
        return categoryRepository.save(category);
    }

    @Override
    public Boolean delete(long categoryId) {
        if (findById(categoryId)==null)
            return false;
        Category category = findById(categoryId);
        for(Category c : findAllByParentCategoryId(categoryId)) {
            categoryRepository.deleteById(c.getCategoryId());
        }
        categoryRepository.deleteById(categoryId);
        return true;
    }
}
