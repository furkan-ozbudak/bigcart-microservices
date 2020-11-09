package com.bigcart.productservice.bigcartproductservice.Controller;

import com.bigcart.productservice.bigcartproductservice.Model.Category;

import com.bigcart.productservice.bigcartproductservice.Model.Product;
import com.bigcart.productservice.bigcartproductservice.Services.CategoryService;
import com.bigcart.productservice.bigcartproductservice.Services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("category")
public class CategoryController {

    @Autowired
    CategoryService categoryService;

    @Autowired
    ProductService productService;

    @GetMapping(value = "/")
    public ResponseEntity findAllCategories() {
        List<Category> categoryList = categoryService.findAll();
        return new ResponseEntity(categoryList, new HttpHeaders(), HttpStatus.OK);
    }

    @PostMapping(value = "/")
    public ResponseEntity addCategory(@RequestBody Map<String, String> request) {
        if (request == null || request.get("name") == null) {
            return new ResponseEntity("category information is missing.", HttpStatus.BAD_REQUEST);
        }
        Category category = new Category();
        if (request.get("parentCategoryId") == null) {
            category.setName(request.get("name"));
            categoryService.save(category);
        } else {
            category.setName(request.get("name"));
            try {
                category.setParentCategoryId(Long.parseLong(request.get("parentCategoryId")));
            } catch (Exception e) {
                return new ResponseEntity("Parent category id should be a number.", HttpStatus.BAD_REQUEST);
            }
            if (categoryService.findById(Long.parseLong(request.get("parentCategoryId"))) == null) {
                return new ResponseEntity("Given parent category doesn't exist.", HttpStatus.BAD_REQUEST);
            }
            category.setParentCategory(categoryService.findById(Long.parseLong(request.get("parentCategoryId"))));
            categoryService.save(category);
        }
        Long id = categoryService.save(category).getCategoryId();
        return new ResponseEntity(id, new HttpHeaders(), HttpStatus.CREATED);
    }

    @PatchMapping(value = "/")
    public ResponseEntity changeCategoryName(@RequestBody Category category) {
        if (category.getCategoryId() == null) {
            return new ResponseEntity("Null category id.", new HttpHeaders(), HttpStatus.BAD_REQUEST);
        }
        Category c = categoryService.findById(category.getCategoryId());
        if(c == null) {
            return new ResponseEntity("No such category.", new HttpHeaders(), HttpStatus.BAD_REQUEST);
        }
        c.setName(category.getName());
        categoryService.save(c);
        return new ResponseEntity("New category name: " + category.getName(), new HttpHeaders(), HttpStatus.OK);
    }

    @GetMapping(value = "/{CategoryId}")
    public ResponseEntity<Category> getCategory(@PathVariable long CategoryId) {

        Category Category = categoryService.findById(CategoryId);

        if (Category == null) {

            return new ResponseEntity<Category>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<Category>(Category, HttpStatus.OK);
    }


//    @PutMapping
//    public ResponseEntity<Category> editCategory(@RequestBody Category Category) {
//
//        HttpHeaders headers = new HttpHeaders();
//        Category Category_toEdit = categoryService.findById(Category.getCategoryId());
//
//        if (Category_toEdit == null) {
//
//            return new ResponseEntity<Category>(HttpStatus.NOT_FOUND);
//        }
//
//        Category updatedCategory = categoryService.update(Category.getCategoryId(), Category);
//
//        return new ResponseEntity<Category>(updatedCategory, headers, HttpStatus.OK);
//    }

    @DeleteMapping(value = "/{categoryId}")
    public ResponseEntity deleteCategory(@PathVariable String categoryId) {
        if(categoryId == null) {
            return new ResponseEntity("Null categoryId is inputted.", new HttpHeaders(), HttpStatus.BAD_REQUEST);
        }
        Long cid = null;
        try {
            cid = Long.parseLong(categoryId);
        }
        catch(Exception e) {
            return new ResponseEntity("Bad category id format inputted", new HttpHeaders(), HttpStatus.BAD_REQUEST);
        }
        Category category = categoryService.findById(cid);
        if(category == null) {
            return new ResponseEntity("Inputted category does not exist.", new HttpHeaders(), HttpStatus.BAD_REQUEST);

        }
        if(category.getParentCategoryId() == null) {
            Product product = productService.findByCategoryId(cid);
            if(product == null) {
                for(Category c : categoryService.findAllByParentCategoryId(cid)) {
                    product = productService.findByCategoryId(c.getCategoryId());
                }
                if(product == null) {
                    categoryService.delete(cid);
                    return new ResponseEntity("Category is deleted.", new HttpHeaders(), HttpStatus.OK);
                }
                else {
                    return new ResponseEntity("Can not delete this category. It includes subcategories which have products registered. ", new HttpHeaders(), HttpStatus.OK);
                }
            }
            else {
                return new ResponseEntity("Can not delete this category. It includes products registered. ", new HttpHeaders(), HttpStatus.OK);
            }
        }
        else {
            Product product = productService.findByCategoryId(cid);
            if(product == null) {
                categoryService.delete(cid);
                return new ResponseEntity("Category is deleted.", new HttpHeaders(), HttpStatus.OK);
            }
            else {
                return new ResponseEntity("Can not delete this category. It includes products registered. ", new HttpHeaders(), HttpStatus.OK);
            }
        }
    }
}
