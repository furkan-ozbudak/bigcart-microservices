package com.bigcart.productservice.bigcartproductservice.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.sql.Insert;

import javax.persistence.*;

@Entity
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long categoryId;

    private String name;

    public Long getParentCategoryId() {
        return parentCategoryId;
    }

    public void setParentCategoryId(Long parentCategoryId) {
        this.parentCategoryId = parentCategoryId;
    }

    @Column(insertable = false,updatable = false)
    private Long parentCategoryId;

    @ManyToOne
    //@JsonIgnore
    @JoinColumn(name = "parentCategoryId")
    private Category parentCategory;

    public Category getParentCategory() {
        return parentCategory;
    }

    public void setParentCategory(Category parentCategory) {
        this.parentCategory = parentCategory;
    }

    public Category() {

    }

    public Long getCategoryId() {
        return categoryId;
    }


    public void setCategoryId(Long id) {
        this.categoryId = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
