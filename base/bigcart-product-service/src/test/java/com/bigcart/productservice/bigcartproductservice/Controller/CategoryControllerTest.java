package com.bigcart.productservice.bigcartproductservice.Controller;

import com.bigcart.productservice.bigcartproductservice.Model.Category;
import com.bigcart.productservice.bigcartproductservice.Services.CategoryService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;

@SpringBootTest
class CategoryControllerTest {


    @Mock
    private CategoryService categoryService;
    @InjectMocks
    CategoryController conroller;

    @Test
    void findAllCategories() {

        Category cat = new Category();
        List<Category> cats= new ArrayList<>();
        cats.add(cat);
        when(categoryService.findAll ()).thenReturn(cats);
        conroller.findAllCategories();
        Mockito.verify(categoryService, times(1)).findAll();




    }


    @Test
    void getCategory() {


        Category cat = new Category();
        when(categoryService.findById(anyLong())).thenReturn(cat);
        conroller.getCategory(1l);
        Mockito.verify(categoryService, times(1)).findById(1l);


    }


}