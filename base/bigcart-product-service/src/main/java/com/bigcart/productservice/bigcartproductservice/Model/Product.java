package com.bigcart.productservice.bigcartproductservice.Model;


import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Product {

    @Id
    @JsonIgnore
    @Column(name = "product_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long productId;
    @Column(insertable = false,updatable = false)
    private Long categoryId;
    private String name;
    private String description;
    private String specifications;

    public Product() {
    }

    //Relations
    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "categoryId")
    private Category category;

    @OneToMany()
    @JsonIgnore
    @JoinColumn(name = "product_id",referencedColumnName = "product_id")
    private List<VendorProduct> vendorProductList = new ArrayList<>();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getSpecifications() {
        return specifications;
    }

    public void setSpecifications(String specs) {
        this.specifications = specs;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }


    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public List<VendorProduct> getProductVendor() {
        return vendorProductList;
    }

    public void setProductVendor(List<VendorProduct> vendorProduct) {
        vendorProductList = vendorProduct;
    }

    public void addVendorProduct(VendorProduct vendorProduct) {
        vendorProductList.add(vendorProduct);
    }

    public void removeVendorProduct(VendorProduct vendorProduct) {
        vendorProductList.remove(vendorProduct);
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }
}
